package com.potato.TutorCall.review.dto;

import com.potato.TutorCall.review.domain.Review;
import com.potato.TutorCall.user.domain.User;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;


@Data
public class TutorReviewResponseDto {
    private ReviewerDto reviewer;
    private String type;
    private int mannerRate;
    private int communicationRate;
    private int professionalismRate;
    private String content;
    private LocalDateTime createdAt;

    public TutorReviewResponseDto(Review review)
    {
        this.reviewer = new ReviewerDto(review.getReviewer());
        this.type = "과외";
        this.mannerRate = review.getMannerRate();
        this.communicationRate =review.getCommunicationRate();
        this.professionalismRate = review.getProfessionalismRate();
        this.content = review.getContent();
        this.createdAt = review.getCreatedAt();
    }
}
