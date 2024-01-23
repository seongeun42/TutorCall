package com.potato.TutorCall.auth.dto;


import com.potato.TutorCall.user.domain.enums.RoleType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserSessionDto {
    private Long id;
    private RoleType roleType;

    @Builder
    public UserSessionDto(Long id, RoleType roleType){
        this.id = id;
        this.roleType = roleType;
    }
}
