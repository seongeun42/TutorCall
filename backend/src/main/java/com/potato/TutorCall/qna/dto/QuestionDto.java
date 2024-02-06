package com.potato.TutorCall.qna.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.potato.TutorCall.qna.domain.Answer;
import com.potato.TutorCall.qna.domain.Question;
import com.potato.TutorCall.tutor.dto.TagDto;
import com.potato.TutorCall.user.dto.UserSimpleDto;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionDto {
  private Long questionId;
  private UserSimpleDto writer;
  private String title;
  private String content;
  private TagDto tag;
  private boolean isEnd;
  private boolean isDelete;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
  private List<AnswerDto> answerList;

  @Builder
  public QuestionDto(Question question) {
    this.questionId = question.getId();
    this.writer = new UserSimpleDto(question.getWriter());
    this.title = question.getTitle();
    this.content = question.getContent();
    this.tag = new TagDto(question.getTag());
    this.isEnd = question.isEnd();
    this.isDelete = question.isDelete();
    this.createdAt = question.getCreatedAt();
    this.modifiedAt = question.getModifiedAt();
  }

  public void setAnswerList(List<Answer> answerList) {
    this.answerList = answerList.stream().map(AnswerDto::new).toList();
  }
}
