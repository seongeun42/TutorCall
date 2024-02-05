package com.potato.TutorCall.inquiry.dto;

import com.potato.TutorCall.inquiry.domain.Inquiry;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InquiryDto {
  private String title;
  private String content;
  private String answer;
  private boolean answerState;
  private LocalDateTime createdAt;
  private LocalDateTime answerAt;

  public InquiryDto(Inquiry inquiry) {
    this.title = inquiry.getTitle();
    this.content = inquiry.getContent();
    ;
    this.answer = inquiry.getAnswer();
    this.answerState = inquiry.isAnswerState();
    this.createdAt = inquiry.getCreatedAt();
    this.answerAt = inquiry.getCreatedAt();
  }
}
