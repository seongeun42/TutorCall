package com.potato.TutorCall.notice.dto;

import com.potato.TutorCall.notice.domain.Notice;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class NoticeResponse {
  private final long noticeId;
  private final String title;
  private final String content;
  private final LocalDateTime createdAt;

  public NoticeResponse(Notice notice) {
    this.noticeId = notice.getId();
    this.title = notice.getTitle();
    this.content = notice.getContent();
    this.createdAt = notice.getCreatedAt();
  }
}
