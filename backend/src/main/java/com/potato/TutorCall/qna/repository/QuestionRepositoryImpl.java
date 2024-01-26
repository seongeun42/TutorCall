package com.potato.TutorCall.qna.repository;

import static com.potato.TutorCall.qna.domain.QQuestion.question;

import com.potato.TutorCall.qna.domain.Question;
import com.potato.TutorCall.qna.dto.QuestionWriteDto;
import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.user.domain.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

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
