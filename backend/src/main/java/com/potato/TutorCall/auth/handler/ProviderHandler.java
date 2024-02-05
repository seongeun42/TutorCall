package com.potato.TutorCall.auth.handler;

import com.potato.TutorCall.auth.dto.oauth.CommonInfo;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface ProviderHandler {
  CommonInfo get(OAuth2User attr);
}
