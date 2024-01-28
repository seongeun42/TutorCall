package com.potato.TutorCall.auth.dto;

import jakarta.mail.internet.InternetAddress;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
public class SendEmailDto {
  private String to;
  private InternetAddress from;
  private String title;
  private String subject;
  private String carbonCopy; // cc : 참조자

  @Builder
  public SendEmailDto(
      String to, InternetAddress from, String title, String subject, String carbonCopy) {
    this.to = to;
    this.from = from;
    this.title = title;
    this.subject = subject;
    this.carbonCopy = carbonCopy;
  }
}
