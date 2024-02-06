package com.potato.TutorCall.review.dto;

import com.potato.TutorCall.review.domain.Review;
import com.potato.TutorCall.review.domain.StudyType;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TutorReviewResponseDto {
  private ReviewerDto reviewer;
  private StudyType type;
  private int mannerRate;
  private int communicationRate;
  private int professionalismRate;
  private String content;
  private LocalDateTime createdAt;

  public TutorReviewResponseDto(Review review) {
    this.reviewer = new ReviewerDto(review.getReviewer());
    this.type = review.getType();
    this.mannerRate = review.getMannerRate();
    this.communicationRate = review.getCommunicationRate();
    this.professionalismRate = review.getProfessionalismRate();
    this.content = review.getContent();
    this.createdAt = review.getCreatedAt();
  }
}
