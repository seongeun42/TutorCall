package com.potato.TutorCall.qna.repository;

import com.potato.TutorCall.qna.domain.Question;
import com.potato.TutorCall.qna.dto.PaginationDto;
import com.potato.TutorCall.qna.dto.QuestionWriteDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
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
    public Question findByQuestionById(int questionId) {

        return queryFactory.selectFrom(question)
                .where(question.id.eq((long) questionId))
                .fetchOne();
    }

    @Override
    public Page<Question> findAll(PaginationDto pagenationDto, Pageable pageable) {
        List<Question> content = queryFactory
                .select(question)
                .from(question)
                .where(
                        question.tag.id.eq(pagenationDto.getTagId()),
                        question.content.contains(pagenationDto.getKeyword())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = queryFactory
                .select(question.count())
                .from(question)
                .where(
                        question.tag.id.eq(pagenationDto.getTagId()),
                        question.content.contains(pagenationDto.getKeyword())
                )
                .fetchOne();

        return new PageImpl<>(content, pageable, count);
    }


    @Override
    @Transactional
    public void writeQuestion(QuestionWriteDto questionWriteDto) {
        entityManager
                .createNativeQuery("insert into question (title, content, tag_id, writer_id) values(?,?,?,?)")
                .setParameter(1, questionWriteDto.getQuestionTitle())
                .setParameter(2, questionWriteDto.getQuestionContent())
                .setParameter(3, questionWriteDto.getTagId())
                .setParameter(4, questionWriteDto.getWriter())
                .executeUpdate();
    }

    @Override
    public void editQuestion(int questionId, QuestionWriteDto questionWriteDto) {
        queryFactory
                .update(question)
                .set(question.title, questionWriteDto.getQuestionTitle())
                .set(question.content, questionWriteDto.getQuestionContent())
                .set(question.tag.id, questionWriteDto.getTagId())
                .execute();
    }

    @Override
    public void deleteQuestionById(int questionId) {
        queryFactory
                .delete(question)
                .where(question.id.eq((long) questionId))
                .execute();
    }
}
