package com.potato.TutorCall.user.dto;


import com.potato.TutorCall.user.domain.User;
import lombok.Builder;
import lombok.Data;

@Data
public class UserDto {
    private Long userId;
    private String nickname;
    private String profile;
    private Integer point;

    @Builder
    public UserDto(Long userId, String nickname, String profile, Integer point) {
        this.userId = userId;
        this.nickname = nickname;
        this.profile = profile;
        this.point = point;
    }
}
