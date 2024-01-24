package com.potato.TutorCall.notice.dto;

import com.potato.TutorCall.notice.domain.Notice;
import lombok.Getter;

@Getter
public class NoticeResponse {
    private final String title;
    private final String content;

    public NoticeResponse(Notice notice) {
        this.title = notice.getTitle();
        this.content = notice.getContent();
    }
}
