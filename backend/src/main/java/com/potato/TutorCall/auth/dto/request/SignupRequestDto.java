package com.potato.TutorCall.auth.dto.request;

import lombok.*;

@Data
public class SignupRequestDto {
  private String email;
  private String password;
  private String nickname;
}
