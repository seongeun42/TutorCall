package com.potato.TutorCall.qna.repository;


import com.potato.TutorCall.qna.domain.Question;
import com.potato.TutorCall.qna.dto.QuestionDto;
import com.potato.TutorCall.qna.dto.QuestionWriteDto;
import com.potato.TutorCall.qna.dto.SearchFormDto;
import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.user.domain.User;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.potato.TutorCall.qna.domain.QQuestion.question;


@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionRepositoryCustom {

  private final JPAQueryFactory queryFactory;
  private final EntityManager entityManager;

  @Override
  @Transactional
  public Long writeQuestion(QuestionWriteDto questionWriteDto, User user, Tag tag) {

        Question question = Question.
                builder().
                title(questionWriteDto.getQuestionTitle()).
                content(questionWriteDto.getQuestionContent()).
                tag(tag).
                writer(user).
                build();

        entityManager.persist(question);
        entityManager.flush();
        return question.getId();
    }

  @Override
  public Page<Question> getList(Pageable pageable, SearchFormDto searchFormDto) {

    List<Question> list =
            queryFactory
                    .selectFrom(question)
                    .where(
                        tagEq(searchFormDto.getTagId()),
                            keywordContains(searchFormDto.getKeyword()),
                            isEndEq(searchFormDto.getIsEnd()),
                            question.isDelete.eq(false)
                    )
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

    Long count = queryFactory
            .select(question.count())
            .from(question)
            .where(
                    tagEq(searchFormDto.getTagId()),
                    keywordContains(searchFormDto.getKeyword()),
                    isEndEq(searchFormDto.getIsEnd()),
                    question.isDelete.eq(false)
            )
            .fetchOne();

    return new PageImpl<>(list, pageable, count);
  }

  private BooleanExpression tagEq(Long TagId){
    return TagId != null ? question.tag.id.eq(TagId) : null;
  }
  private BooleanExpression keywordContains(String keyword){
    return keyword!= null ? question.content.contains(keyword) : null;
  }
  private BooleanExpression isEndEq(Boolean isEnd){
    return isEnd!=null ? question.isEnd.eq(isEnd) : null;
  }



  @Override
  @Transactional
  public long editQuestion(int questionId, QuestionWriteDto questionWriteDto, User user, Tag tag) {

    long count = 0;

    JPAUpdateClause updateClause =
        queryFactory
            .update(question)
            .set(question.title, questionWriteDto.getQuestionTitle())
            .set(question.content, questionWriteDto.getQuestionContent())
            .where(question.id.eq((long) questionId), question.writer.id.eq(user.getId()));

    if (tag != null) updateClause.set(question.tag.id, tag.getId());

    count = updateClause.execute();
    entityManager.flush();
    entityManager.clear();
    return count;
  }
}
