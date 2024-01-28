package com.potato.TutorCall.review.dto;

import lombok.Data;

@Data
public class ReviewRequestDto {

    private Integer mannerRate;
    private Integer communicationRate;
    private Integer professionalismRate;
    private String content;

}