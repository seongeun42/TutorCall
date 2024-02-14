package com.potato.TutorCall.auth.dto.request;

import com.potato.TutorCall.user.domain.enums.RoleType;
import lombok.*;

@Data
public class SignupRequestDto {

  private String email;
  private String password;
  private String nickname;
  private RoleType role;

}
