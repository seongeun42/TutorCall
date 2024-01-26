package com.potato.TutorCall.qna.repository;

import com.potato.TutorCall.qna.domain.Question;
import com.potato.TutorCall.qna.dto.AnswerWriteDto;
import com.potato.TutorCall.tutor.domain.Tutor;

public interface AnswerRepositoryCustom {

  Long writeAnswer(AnswerWriteDto answerWriteDto, Tutor tutor, Question question);
}
