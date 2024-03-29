package com.potato.TutorCall.common.dto;

import lombok.Data;

@Data
public class CreatedResponseDto {

  private Long id;
  private String message;

  public CreatedResponseDto(Long id, String message) {
    this.id = id;
    this.message = message;
  }
}
