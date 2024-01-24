package com.potato.TutorCall.qna.repository;

import com.potato.TutorCall.qna.domain.QQuestion;
import com.potato.TutorCall.qna.domain.Question;
import com.potato.TutorCall.qna.dto.PaginationDto;
import com.potato.TutorCall.qna.dto.QuestionWriteDto;
import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.tutor.repository.TagRepository;
import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.repository.UserRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.potato.TutorCall.qna.domain.QQuestion.question;

@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    @Transactional
    public Long writeQuestion(QuestionWriteDto questionWriteDto, User user, Tag tag) {

        //user가 null이어도 insert가 진행되어서 억지로 막아놓음..

        Question question = Question.
                builder().
                title(questionWriteDto.getQuestionTitle()).
                content(questionWriteDto.getQuestionContent()).
                tag(tag).
                writer(user).
                build();
        entityManager.persist(question);
        return question.getId();
    }

    @Override
    @Transactional
    public long editQuestion(int questionId, QuestionWriteDto questionWriteDto, User user, Tag tag) {

        long count = 0;

        JPAUpdateClause updateClause= queryFactory.update(question)
                .set(question.title, questionWriteDto.getQuestionTitle())
                .set(question.content, questionWriteDto.getQuestionContent())
                .where(question.id.eq((long) questionId), question.writer.id.eq(user.getId()));

        if(tag != null) updateClause.set(question.tag.id, tag.getId());

        count = updateClause.execute();
        entityManager.flush();
        entityManager.clear();
        return count;

    }


}
