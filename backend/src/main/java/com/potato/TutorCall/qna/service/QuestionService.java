package com.potato.TutorCall.qna.service;

import com.potato.TutorCall.exception.customException.InvalidException;
import com.potato.TutorCall.exception.customException.NotFoundException;
import com.potato.TutorCall.qna.domain.Answer;
import com.potato.TutorCall.qna.domain.Question;
import com.potato.TutorCall.qna.dto.CommonResponseDto;
import com.potato.TutorCall.qna.dto.QuestionDto;
import com.potato.TutorCall.qna.dto.QuestionWriteDto;
import com.potato.TutorCall.qna.dto.SearchFormDto;
import com.potato.TutorCall.qna.repository.QuestionRepository;
import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.tutor.repository.TagRepository;
import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;


@Service
public class QuestionService {

  private final QuestionRepository questionRepository;
  private final TagRepository tagRepository;
  private final UserRepository userRepository;

  public QuestionService(
      QuestionRepository questionRepository,
      TagRepository tagRepository,
      UserRepository userRepository) {
    this.questionRepository = questionRepository;
    this.tagRepository = tagRepository;
    this.userRepository = userRepository;
  }

@Transactional
  public CommonResponseDto question(int questionId) {

    Question q = (Question)
            questionRepository
                .findQuestionByIdAndIsDelete((long) questionId, false)
                .orElseThrow(() -> new NotFoundException("질문 조회 실패"));
    QuestionDto questionDto = new QuestionDto(q);
    questionDto.setAnswerList(q.getAnswerList()
            .stream().filter(
                    (answer -> !answer.isDelete())
            ).toList());

    return CommonResponseDto.builder().question(questionDto).build();
  }

  public CommonResponseDto writeQuestion(QuestionWriteDto questionWriteDto) {

    Tag tag =
            tagRepository
                    .findById(questionWriteDto.getTagId())
                    .orElseThrow(() -> new NotFoundException("질문 작성 실패"));
    User user =
            userRepository
                    .findById(questionWriteDto.getWriterId())
                    .orElseThrow(() -> new NotFoundException("질문 작성 실패"));



    Long questionId = null;

    questionId = questionRepository.writeQuestion(questionWriteDto, user, tag);

    if (questionId == null) throw new NotFoundException("질문 작성 실패");

    return CommonResponseDto.builder().questionId(questionId).message("질문 게시글이 생성되었습니다.").build();
  }

  public CommonResponseDto questionAll(Pageable pageable, SearchFormDto searchFormDto) {

    Page<QuestionDto> list =
            questionRepository.getList(pageable, searchFormDto)
                    .map(QuestionDto::new);

    return CommonResponseDto.builder().questions(list).build();
  }

  public CommonResponseDto editQuestion(
      int questionId, QuestionWriteDto questionWriteDto, long userId) {

    Long count = null;

    Tag tag =
        tagRepository
            .findById(questionWriteDto.getTagId())
            .orElseThrow(() -> new NotFoundException("질문 수정 실패"));
    User user =
        userRepository
            .findById(questionWriteDto.getWriterId())
            .orElseThrow(() -> new NotFoundException("질문 수정 실패"));

    User requestUser =
        userRepository.findById((long) userId).orElseThrow(() -> new NotFoundException("질문 수정 실패"));

    Question editTarget =
        questionRepository
            .findById((long) questionId)
            .orElseThrow(() -> new NotFoundException("질문 수정 실패"));

    if (!editTarget.getWriter().getId().equals(user.getId())) throw new InvalidException("수정 권한 없음");

    count = questionRepository.editQuestion(questionId, questionWriteDto, user, tag);
    if (count == 0) throw new NotFoundException("질문 수정 실패");

    return CommonResponseDto.builder().message("질문 게시글이 수정되었습니다.").build();
  }

  @Transactional
  public CommonResponseDto deleteQuestion(int questionId, long userId) {

    User requestUser =
        userRepository.findById(userId).orElseThrow(() -> new NotFoundException("질문 삭제 실패"));
    Question editTarget =
        questionRepository
            .findById((long) questionId)
            .orElseThrow(() -> new NotFoundException("질문 삭제 실패"));

    if (!editTarget.getWriter().getId().equals(requestUser.getId())) throw new InvalidException("수정 권한 없음");

    int count =
        questionRepository.deleteQuestion(
            (long) questionId, true);

    if (count == 0) throw new NotFoundException("질문 삭제 실패");

    for(Answer answer: editTarget.getAnswerList()) answer.deleted();

    return CommonResponseDto.builder().message("질문 삭제 완료.").build();
  }
}
