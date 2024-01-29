package com.potato.TutorCall.review.dto;

import com.potato.TutorCall.review.domain.Review;
import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.dto.UserSimpleDto;
import lombok.Builder;
import lombok.Data;

@Data
public class ReviewDto {

    private final UserSimpleDto tutor;
    private final UserSimpleDto reviewer;
    private final int mannerRate;
    private final int communicationRate;
    private final int professionalismRate;
    private final String content;

    @Builder
    public ReviewDto(Review review, User tutor, User reviewer) {
        this.tutor = new UserSimpleDto(tutor != null ? tutor : review.getTutor().getUser());
        this.reviewer = new UserSimpleDto(reviewer != null ? reviewer : review.getReviewer());
        this.mannerRate = review.getMannerRate();
        this.communicationRate = review.getCommunicationRate();
        this.professionalismRate = review.getProfessionalismRate();
        this.content = review.getContent();
    }

}
