package com.potato.TutorCall.review.dto;

import com.potato.TutorCall.review.domain.Review;
import lombok.Data;

@Data
public class ReviewRequestDto {

    private Integer mannerRate;
    private Integer communicationRate;
    private Integer professionalismRate;
    private String content;

    public ReviewRequestDto(Review review) {
        this.mannerRate = mannerRate;
        this.communicationRate = communicationRate;
        this.professionalismRate = professionalismRate;
        this.content = content;
    }
}