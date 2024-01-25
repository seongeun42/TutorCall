package com.potato.TutorCall.qna.repository;

import com.potato.TutorCall.qna.domain.Answer;
import com.potato.TutorCall.qna.domain.Question;
import com.potato.TutorCall.qna.dto.AnswerWriteDto;
import com.potato.TutorCall.tutor.domain.Tutor;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class AnswerRepositoryImpl implements AnswerRepositoryCustom{

    private final EntityManager entityManager;
    @Override
    @Transactional
    public Long writeAnswer(AnswerWriteDto answerWriteDto, Tutor tutor, Question question) {
        Answer answer = Answer.builder()
                .content(answerWriteDto.getAnswerContent())
                .tutor(tutor)
                .question(question)
                .build();

        entityManager.persist(answer);
        entityManager.flush();

        return answer.getId();
    }
}
