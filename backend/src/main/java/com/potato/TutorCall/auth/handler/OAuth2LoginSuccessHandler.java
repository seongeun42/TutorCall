package com.potato.TutorCall.auth.handler;

import com.potato.TutorCall.auth.SessionKey;
import com.potato.TutorCall.auth.dto.oauth.CommonInfo;
import com.potato.TutorCall.auth.service.AuthService;
import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.domain.enums.RoleType;
import com.potato.TutorCall.user.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

  private final UserService userService;
  private final AuthService authService;
  private final List<ProviderHandler> providerHandlers;

  @Value("${frontend.url}")
  private String frontendUrl;

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws ServletException, IOException {
    OAuth2AuthenticationToken oAuth2AuthenticationToken =
        (OAuth2AuthenticationToken) authentication;
    //        super.onAuthenticationSuccess(request, response, authentication);

    DefaultOAuth2User principal = (DefaultOAuth2User) oAuth2AuthenticationToken.getPrincipal();

    String provider = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
    ProviderHandler providerHandler =
        providerHandlers.stream()
            .filter(h -> h.getClass().getSimpleName().toLowerCase().startsWith(provider))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("제공하지 않는 Provider입니다."));

    CommonInfo comm = providerHandler.get(principal);

    // 유저 있는 지 확인
    User user = this.userService.findUserByEmail(comm.getEmail());

    if (user == null) {

      User newUser =
          User.builder()
              .email(comm.getEmail())
              .nickname(comm.getName())
              .sns(comm.getSnsType())
              .role(RoleType.USER)
              .build();
      user = this.userService.save(newUser);
    }
    this.setAlwaysUseDefaultTargetUrl(true);
    this.setDefaultTargetUrl(frontendUrl);

    // TODO: SESSION설정
    HttpSession session = request.getSession(true);
    authService.saveUserInfoToSession(session, SessionKey.USER, user);

    response.sendRedirect(frontendUrl);
  }
}
