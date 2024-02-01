package com.potato.TutorCall.auth.handler;

import com.potato.TutorCall.auth.dto.oauth.CommonInfo;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component

public class KakaoProviderHandler implements ProviderHandler{
    @Override
    public CommonInfo get(OAuth2User attr) {
        Map<String, Object> properties = attr.getAttribute("properties");
        return CommonInfo
                .builder()
                .email((String) properties.get("email"))
                .name((String) properties.get("name"))
                .build();
    }
}
