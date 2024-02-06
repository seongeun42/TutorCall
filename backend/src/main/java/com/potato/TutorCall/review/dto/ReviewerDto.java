package com.potato.TutorCall.review.dto;

import com.potato.TutorCall.user.domain.User;
import lombok.Data;

@Data
public class ReviewerDto {
  private Long userId;
  private String nickname;
  private String profile;

  public ReviewerDto(User user) {
    this.userId = user.getId();
    this.nickname = user.getNickname();
    this.profile = user.getProfile();
  }
}
