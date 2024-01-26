package com.potato.TutorCall.qna.controller;

import com.potato.TutorCall.auth.SessionKey;
import com.potato.TutorCall.auth.dto.UserSessionDto;
import com.potato.TutorCall.config.CommonResponses;
import com.potato.TutorCall.qna.dto.AnswerWriteDto;
import com.potato.TutorCall.qna.dto.QuestionWriteDto;
import com.potato.TutorCall.qna.dto.SearchFormDto;
import com.potato.TutorCall.qna.service.AnswerService;
import com.potato.TutorCall.qna.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/qna")
@Tag(name = "qna API", description = "Q&A 등록을 위한 API")
public class QnaController {

  private final QuestionService questionService;
  private final AnswerService answerService;

  public QnaController(QuestionService questionService, AnswerService answerService) {
    this.questionService = questionService;
    this.answerService = answerService;
  }

  @CommonResponses
  @Operation(summary = "Q&A 게시글 선택 조회", description = "questionId에 맞는 q&a 게시글 스펙 반환")
  @GetMapping("/question/{questionId}")
  public ResponseEntity<?> question(@PathVariable("questionId") int questionId) {
    return questionService.question(questionId);
  }

  @CommonResponses
  @Operation(summary = "Q&A 게시판 전체 조회", description = "전체 게시글 가져옴")
  @GetMapping("/question")
  public ResponseEntity<?> questionAll(Pageable pageable, SearchFormDto pageNationDto) {
    return questionService.questionAll(pageable, pageNationDto);
  }

  @CommonResponses
  @Operation(summary = "새 질문 작성", description = "새 질문 작성")
  @PostMapping("/question")
  public ResponseEntity<?> writeQuestion(@RequestBody QuestionWriteDto questionWriteDto) {
    return questionService.writeQuestion(questionWriteDto);
  }

  @CommonResponses
  @Operation(summary = "질문글 수정", description = "질문글 수정")
  @PatchMapping("/question/{questionId}")
  public ResponseEntity<?> editQuestion(
      @PathVariable("questionId") int questionId,
      @RequestBody QuestionWriteDto questionWriteDto,
      HttpSession session) {
    UserSessionDto userSessionDto = (UserSessionDto) session.getAttribute(SessionKey.USER);
    return questionService.editQuestion(questionId, questionWriteDto, userSessionDto.getId());
  }

  @CommonResponses
  @Operation(summary = "질문글 삭제", description = "질문글 삭제")
  @DeleteMapping("/question/{questionId}")
  public ResponseEntity<?> deleteQuestion(
      @PathVariable("questionId") int questionId, HttpSession session) {

    UserSessionDto userSessionDto = (UserSessionDto) session.getAttribute(SessionKey.USER);
    return questionService.deleteQuestion(questionId, userSessionDto.getId());
  }

  @CommonResponses
  @Operation(summary = "답변 작성", description = "답변 작성")
  @PostMapping("/answer")
  public ResponseEntity<?> writeAnswer(
      @RequestBody AnswerWriteDto answerWriteDto, HttpSession session) {
    UserSessionDto userSessionDto = (UserSessionDto) session.getAttribute(SessionKey.USER);
    return answerService.writeAnswer(answerWriteDto, userSessionDto.getId());
  }

  @CommonResponses
  @Operation(summary = "답변 삭제", description = "답변을 삭제한다")
  @PatchMapping("/answer/{answerId}")
  public ResponseEntity<?> deleteAnswer(
      @PathVariable("answerId") int answerId, HttpSession session) {
    UserSessionDto userSessionDto = (UserSessionDto) session.getAttribute(SessionKey.USER);
    return answerService.deleteAnswer(answerId, userSessionDto.getId());
  }

  @CommonResponses
  @Operation(summary = "답변 채택", description = "답변을 채택한다")
  @PatchMapping("/answer/selection/{answerId}")
  public ResponseEntity<?> chooseAnswer(
      @PathVariable("answerId") int answerId, HttpSession session) {
    UserSessionDto userSessionDto = (UserSessionDto) session.getAttribute(SessionKey.USER);
    return answerService.chooseAnswer(answerId, userSessionDto.getId());
  }
}
