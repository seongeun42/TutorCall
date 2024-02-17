package com.potato.TutorCall.mypage.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
public class ProfileUpdateResDto {

    private String message;
    private String url;
    @Builder
    public ProfileUpdateResDto(String message, String url){
        this.message = message;
        this.url = url;
    }
}


