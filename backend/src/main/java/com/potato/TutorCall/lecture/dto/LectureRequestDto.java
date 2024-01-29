package com.potato.TutorCall.lecture.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LectureRequestDto {

    private String promotionTitle;
    private String promotionContent;
    private Integer maxParticipant;
    private LocalDateTime promotionDue;
    private Integer price;
    private Long tagId;

}
