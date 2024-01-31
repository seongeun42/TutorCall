package com.potato.TutorCall.auth.dto.oauth;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class OAuth2LoginUserKakaoDto implements OAuth2LoginUserDto<CommonInfo>{
    private Map<String,Object> attributes = new HashMap<>(); // 받은거
    private CommonInfo commonInfo;

    @Override
    public CommonInfo getAttribute() {
        return this.commonInfo;
    }

    @Override
    public CommonInfo toEntity() {
//        Map<String, Object> obj = attributes.get("response");
//
//        ObjectMapper

        return null;
    }

}
