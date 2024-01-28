package com.potato.TutorCall.auth.dto.request;

import lombok.Data;

@Data
public class SignupRequestDto {
  private final String email;
  private final String password;
  private final String nickname;
}
