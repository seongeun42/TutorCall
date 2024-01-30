package com.potato.TutorCall.user.dto;


import lombok.Data;

@Data
public class UserDto {
    private Long userId;
    private String nickname;
    private String profile;
    private Integer point;
}
