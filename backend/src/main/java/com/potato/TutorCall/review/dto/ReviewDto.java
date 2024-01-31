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
    public ReviewDto(Review test, User tutor, User reviewer) {
        this.tutor = new UserSimpleDto(tutor != null ? tutor : test.getTutor().getUser());
        this.reviewer = new UserSimpleDto(reviewer != null ? reviewer : test.getReviewer());
        this.mannerRate = test.getMannerRate();
        this.communicationRate = test.getCommunicationRate();
        this.professionalismRate = test.getProfessionalismRate();
        this.content = test.getContent();
    }

}
