package com.potato.TutorCall.review.dto;


import com.potato.TutorCall.review.domain.Review;
import com.potato.TutorCall.review.domain.StudyType;
import lombok.Data;


import java.time.LocalDateTime;

@Data
public class UserReviewResponseDto {

    private TutorDto tutor;
    private ReviewerDto reviewer;
    private StudyType type;
    private int mannerRate;
    private int communicationRate;
    private int professionalismRate;
    private String content;
    private LocalDateTime createdAt;

    public UserReviewResponseDto(Review review){
        this.tutor = new TutorDto(
                review.getTutor().getIntroduction(),
                review.getTutor().isActive(),
                review.getTutor().getMannerRate(),
                review.getTutor().getCommunicationRate(),
                review.getTutor().getProfessionalismRate()
        );


        this.reviewer = new ReviewerDto(review.getReviewer());
        this.type = review.getType();
        this.mannerRate = review.getMannerRate();
        this.communicationRate =review.getCommunicationRate();
        this.professionalismRate = review.getProfessionalismRate();
        this.content = review.getContent();
        this.createdAt = review.getCreatedAt();
    }


}

