package com.potato.TutorCall.tutorcall.dto.response;

import com.potato.TutorCall.tutor.dto.TagDto;
import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.dto.UserSimpleDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TutorCallMessage {

    private UUID id;
    private String title;
    private String content;
    private UserSimpleDto user;
    private TagDto tag;
    private LocalDateTime created;
    private Integer matched;

    @Builder
    public TutorCallMessage(UUID id, String title, String content, UserSimpleDto user, TagDto tag) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.tag = tag;
        this.created = LocalDateTime.now();
        this.matched = 0;
    }

}
