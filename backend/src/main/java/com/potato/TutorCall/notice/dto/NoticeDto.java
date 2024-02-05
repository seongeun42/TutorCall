package com.potato.TutorCall.notice.dto;

import com.potato.TutorCall.notice.domain.Notice;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class NoticeDto {

  private String title;
  private String content;

  public Notice toEntity() {
    return Notice.builder().title(title).content(content).build();
  }
}
