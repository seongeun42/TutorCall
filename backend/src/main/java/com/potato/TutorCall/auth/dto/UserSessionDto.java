package com.potato.TutorCall.auth.dto;

import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.domain.enums.RoleType;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class UserSessionDto implements Serializable {
  private Long id;
  private RoleType roleType;

  @Builder
  public UserSessionDto(Long id, RoleType roleType) {
    this.id = id;
    this.roleType = roleType;
  }

  public static UserSessionDto of(User user) {
    return UserSessionDto.builder().id(user.getId()).roleType(user.getRole()).build();
  }
}
