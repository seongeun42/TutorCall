package com.potato.TutorCall.lecture.dto;

import com.potato.TutorCall.lecture.domain.Lecture;
import com.potato.TutorCall.review.domain.Review;
import com.potato.TutorCall.review.dto.ReviewDto;
import com.potato.TutorCall.tutor.dto.TagDto;
import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.dto.UserSimpleDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class LectureResponseDto {

    private final Long id;
    private final UserSimpleDto tutor;
    private final TagDto tag;
    private final int maxParticipants;
    private final int participants;
    private final String liveUrl;
    private final boolean lectureState;
    private final LocalDateTime lectureStartAt;
    private final LocalDateTime lectureEndAt;
    private final int price;
    private List<ReviewDto> reviews;

    public LectureResponseDto(Lecture lecture, User tutor, List<Review> reviews) {
        this.id = lecture.getId();
        this.tutor = new UserSimpleDto(tutor);
        this.tag = new TagDto(lecture.getTag());
        this.maxParticipants = lecture.getMaxParticipants();
        this.participants = lecture.getParticipants();
        this.liveUrl = lecture.getLiveUrl();
        this.lectureState = lecture.isLectureState();
        this.lectureStartAt = lecture.getLectureStartAt();
        this.lectureEndAt = lecture.getLectureEndAt();
        this.price = lecture.getPrice();
        if (reviews != null) {
            this.reviews = reviews.stream().map(r -> ReviewDto.builder().review(r).tutor(tutor).build()).toList();
        }
    }

}
