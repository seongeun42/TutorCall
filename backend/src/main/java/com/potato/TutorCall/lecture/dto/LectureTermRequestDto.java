package com.potato.TutorCall.lecture.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class LectureTermRequestDto {

  private LocalDateTime start;
  private LocalDateTime end;
}
