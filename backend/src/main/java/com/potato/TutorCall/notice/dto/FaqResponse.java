package com.potato.TutorCall.notice.dto;

import com.potato.TutorCall.notice.domain.Faq;
import lombok.Getter;

@Getter
public class FaqResponse {
  private final long faqId;
  private final String question;
  private final String answer;

  public FaqResponse(Faq faq) {
    this.faqId = faq.getId();
    this.question = faq.getQuestion();
    this.answer = faq.getAnswer();
  }
}
