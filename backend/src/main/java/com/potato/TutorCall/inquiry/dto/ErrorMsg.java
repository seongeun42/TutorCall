package com.potato.TutorCall.inquiry.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMsg {
  private LocalDateTime timeStamp;
  private String message;
}
