package com.potato.TutorCall.lecture.dto;

import com.potato.TutorCall.lecture.domain.Lecture;
import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.tutor.dto.TagDto;
import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.dto.UserSimpleDto;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LectureListResponseDto {

    private final Long id;
    private final String title;
    private final String content;
    private final TagDto tag;
    private final UserSimpleDto tutor;
    private final Boolean promotionState;
    private final LocalDateTime createdAt;

    public LectureListResponseDto(Lecture lecture) {
        this.id = lecture.getId();
        this.title = lecture.getPromotionTitle();
        this.content = lecture.getPromotionContent();
        this.tag = new TagDto(lecture.getTag());
        this.tutor = new UserSimpleDto(lecture.getTutor().getUser());
        this.promotionState = lecture.isPromotionState();
        this.createdAt = lecture.getPromotionCreatedAt();
    }

    @QueryProjection
    public LectureListResponseDto(Long id, String title, String content, Tag tag, User tutor, Boolean promotionState, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.tag = new TagDto(tag);
        this.tutor = new UserSimpleDto(tutor);
        this.promotionState = promotionState;
        this.createdAt = createdAt;
    }
}
