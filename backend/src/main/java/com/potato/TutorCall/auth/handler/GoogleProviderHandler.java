package com.potato.TutorCall.auth.handler;

import com.potato.TutorCall.auth.dto.oauth.CommonInfo;
import com.potato.TutorCall.user.domain.enums.SnsType;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;


@Component
public class GoogleProviderHandler implements ProviderHandler {
    @Override
    public CommonInfo get(OAuth2User attribute) {
        return CommonInfo
                .builder()
                .snsType(SnsType.GOOGLE)
                .email((String) attribute.getAttribute("email"))
                .name((String) attribute.getAttribute("name"))
                .profile((String) attribute.getAttribute("picture"))
                .build();
    }
}
