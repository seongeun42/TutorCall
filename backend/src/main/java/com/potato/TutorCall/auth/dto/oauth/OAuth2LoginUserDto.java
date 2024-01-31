package com.potato.TutorCall.auth.dto.oauth;

public interface OAuth2LoginUserDto<T> {
    public T getAttribute();
    public T toEntity();
}
