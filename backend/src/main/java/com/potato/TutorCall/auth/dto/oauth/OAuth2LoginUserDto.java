package com.potato.TutorCall.auth.dto.oauth;

public interface OAuth2LoginUserDto<T> {
    public T getAttribute();
    public T toEntity();
}

/*
* OAuth2User attribute에서 가져오기
*
*
* */