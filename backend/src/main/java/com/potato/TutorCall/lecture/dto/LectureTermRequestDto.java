package com.potato.TutorCall.lecture.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LectureTermRequestDto {

    private LocalDateTime start;
    private LocalDateTime end;

}
