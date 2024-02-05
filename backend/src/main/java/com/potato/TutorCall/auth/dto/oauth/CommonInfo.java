package com.potato.TutorCall.auth.dto.oauth;

import com.potato.TutorCall.user.domain.enums.SnsType;
import lombok.Builder;
import lombok.Data;

@Data
public class CommonInfo {
  private String email;
  private String name;
  private String profile;
  private SnsType snsType;

  @Builder
  public CommonInfo(String email, String name, String profile, SnsType snsType) {
    this.email = email;
    this.name = name;
    this.profile = profile;
    this.snsType = snsType;
  }
}
