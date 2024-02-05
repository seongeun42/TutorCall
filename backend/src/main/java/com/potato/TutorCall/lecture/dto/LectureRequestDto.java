package com.potato.TutorCall.lecture.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class LectureRequestDto {

  private String promotionTitle;
  private String promotionContent;
  private Integer maxParticipant;
  private LocalDateTime promotionDue;
  private Integer price;
  private Long tagId;
}
