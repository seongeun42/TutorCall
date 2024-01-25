package com.potato.TutorCall.notice.dto;

import com.potato.TutorCall.notice.domain.Notice;
import lombok.Getter;

import java.time.LocalDateTime;

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
