package com.potato.TutorCall.auth.dto;

import com.potato.TutorCall.user.domain.enums.RoleType;
import lombok.Builder;
import lombok.Data;

@Data
public class LoginResponseDto {

    private Long id;
    private String email;
    private String nickname;
    private String profile;
    private RoleType role;

    @Builder
    public LoginResponseDto(Long id, String email, String nickname, String profile, RoleType role) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.profile = profile;
        this.role = role;
    }

}