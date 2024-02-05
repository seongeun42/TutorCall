package com.potato.TutorCall.qna.dto;

import com.potato.TutorCall.qna.domain.Answer;
import com.potato.TutorCall.user.dto.UserSimpleDto;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
public class AnswerDto {

  private Long id;
  private UserSimpleDto tutor;
  private String content;
  private boolean isChosen;
  private boolean isDelete;
  private LocalDateTime createAt;
  private LocalDateTime modifiedAt;

  @Builder
  public AnswerDto(Answer answer) {
    this.id = answer.getId();
    this.tutor = new UserSimpleDto(answer.getTutor().getUser());
    this.content = answer.getContent();
    this.isChosen = answer.isChosen();
    this.isDelete = answer.isDelete();
    this.createAt = answer.getCreateAt();
    this.modifiedAt = answer.getModifiedAt();
  }
}
