package com.potato.TutorCall.notice.dto;

import com.potato.TutorCall.notice.domain.Faq;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FaqDto {
  private String question;
  private String answer;

  public Faq toEntity() {
    return Faq.builder().question(question).answer(answer).build();
  }
}
