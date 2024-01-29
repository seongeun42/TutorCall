package com.potato.TutorCall.auth.handler;

import com.potato.TutorCall.user.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
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

  @Value("${frontend.url}:${frontend.port}")
  private String frontendUrl;

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws ServletException, IOException {
    OAuth2AuthenticationToken oAuth2AuthenticationToken =
        (OAuth2AuthenticationToken) authentication;
    //        super.onAuthenticationSuccess(request, response, authentication);

    DefaultOAuth2User principal = (DefaultOAuth2User) oAuth2AuthenticationToken.getPrincipal();
    Map<String, Object> attributes = principal.getAttributes();
    // SNSTYPE
    // SnsType snsType = attributes.getOrDefault();

    // TODO: 전체적 모듈화 필요
    //          String email = (String) attributes.getOrDefault("email", "");
    //          String nickname = (String) attributes.getOrDefault("nickname", "");
    //          String snsTypeAsString = (String)
    //   oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
    //          String profile = (String) attributes.getOrDefault("profile","");
    //
    //          //유저 있는 지 확인
    //          User user = this.userService.findUserByEmail(email);
    //
    //          if(user == null){
    //              //TODO: 바꾸기
    //              SnsType snsType;
    //              if("kakao".equals(snsTypeAsString)) snsType = SnsType.KAKAO;
    //              if("naver".equals(snsTypeAsString)) snsType = SnsType.NAVER;
    //              User newUser= User
    //                      .builder()
    //                      .email(email)
    //                      .nickname(nickname)
    //                      .sns(SnsType.KAKAO)
    //                      .role(RoleType.USER)
    //                      .profile(profile)
    //                      .build();
    //              user = this.userService.save(newUser);
    //          }
    //
    //          HttpSession session = request.getSession(true);
    //          UserSessionDto userSessionDto = UserSessionDto
    //                  .builder()
    //                  .id(user.getId())
    //                  .roleType(user.getRole())
    //                  .build();
    //          session.setAttribute(SessionKey.USER, userSessionDto);
    //          this.setAlwaysUseDefaultTargetUrl(true);
    //          this.setDefaultTargetUrl(frontendUrl);
    // 성공 시 FRONTEND 주소로 리다이렉트
    // TODO: SESSION설정
    response.sendRedirect(frontendUrl);
  }
}
