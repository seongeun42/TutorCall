package com.potato.TutorCall.qna.service;

import com.potato.TutorCall.exception.customException.InvalidException;
import com.potato.TutorCall.exception.customException.NotFoundException;
import com.potato.TutorCall.qna.domain.Answer;
import com.potato.TutorCall.qna.domain.Question;
import com.potato.TutorCall.qna.dto.AnswerWriteDto;
import com.potato.TutorCall.qna.dto.CommonResponseDto;
import com.potato.TutorCall.qna.repository.AnswerRepository;
import com.potato.TutorCall.qna.repository.QuestionRepository;
import com.potato.TutorCall.tutor.domain.Tutor;
import com.potato.TutorCall.tutor.repository.TutorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnswerService {

  private final TutorRepository tutorRepository;
  private final QuestionRepository questionRepository;
  private final AnswerRepository answerRepository;

  public AnswerService(
      TutorRepository tutorRepository,
      QuestionRepository questionRepository,
      AnswerRepository answerRepository) {
    this.tutorRepository = tutorRepository;
    this.questionRepository = questionRepository;
    this.answerRepository = answerRepository;
  }

  public ResponseEntity<?> writeAnswer(AnswerWriteDto answerWriteDto, long userId) {

    answerWriteDto.setTutorUserId(userId);
    Tutor tutor =
        tutorRepository
            .findById(answerWriteDto.getTutorUserId())
            .orElseThrow(() -> new NotFoundException("답변 작성 실패"));
    Question targetQuestion =
        questionRepository
            .findById(answerWriteDto.getQuestionId())
            .orElseThrow(() -> new NotFoundException("답변 작성 실패"));

    Long answerId = null;
    CommonResponseDto commonResponseDto;

    answerId = answerRepository.writeAnswer(answerWriteDto, tutor, targetQuestion);

    if (answerId == null) throw new NotFoundException("질문 작성 실패");

    commonResponseDto =
        CommonResponseDto.builder().answerId(answerId).message("답변이 생성되었습니다.").build();

    return ResponseEntity.ok(commonResponseDto);
  }

  @Transactional
  public ResponseEntity<?> deleteAnswer(int answerId, long userId) {

    CommonResponseDto commonResponseDto;
    Answer targetAnswer =
        answerRepository
            .findById((long) answerId)
            .orElseThrow(() -> new NotFoundException("질문 삭제 실패"));

    if (!targetAnswer.getTutor().getId().equals(userId)) throw new InvalidException("삭제 권한 없음");

    int count = answerRepository.deleteQuestion((long) answerId, true);
    if (count == 0) throw new NotFoundException("질문 삭제 실패");

    commonResponseDto = CommonResponseDto.builder().message("답변 삭제 완료.").build();
    return ResponseEntity.ok(commonResponseDto);
  }

  @Transactional
  public ResponseEntity<?> chooseAnswer(int answerId, long userId) {

    CommonResponseDto commonResponseDto;

    Answer targetAnswer =
        answerRepository
            .findById((long) answerId)
            .orElseThrow(() -> new NotFoundException("답변 채택 실패"));

    if (!targetAnswer.getQuestion().getWriter().getId().equals(userId))
      throw new InvalidException("권한 없음");

    int count = answerRepository.chooseAnswer((long) answerId, true);
    if (count == 0) throw new NotFoundException("답변 채택 실패");

    commonResponseDto = CommonResponseDto.builder().message("답변 채택 완료.").build();
    return ResponseEntity.ok(commonResponseDto);
  }
}
