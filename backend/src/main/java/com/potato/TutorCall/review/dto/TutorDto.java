package com.potato.TutorCall.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TutorDto {

  private String introduction;
  private boolean isActive;

  private double mannerRate;

  private double communicationRate;

  private double professionalismRate;

  public TutorDto(
      String introduction,
      boolean isActive,
      double mannerRate,
      double communicationRate,
      double professionalismRate) {
    this.introduction = introduction;
    this.isActive = isActive;
    this.mannerRate = mannerRate;
    this.communicationRate = communicationRate;
    this.professionalismRate = professionalismRate;
  }
}
