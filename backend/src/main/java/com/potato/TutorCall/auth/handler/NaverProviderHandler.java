package com.potato.TutorCall.auth.handler;

import com.potato.TutorCall.auth.dto.oauth.CommonInfo;
import java.util.Map;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class NaverProviderHandler implements ProviderHandler {
  @Override
  public CommonInfo get(OAuth2User attr) {
    Map<String, Object> res = attr.getAttribute("response");
    return CommonInfo.builder()
        .email((String) res.get("email"))
        .name((String) res.get("name"))
        .build();
  }
}
