package com.potato.TutorCall.config;

import java.util.Arrays;

import com.potato.TutorCall.auth.handler.OAuth2LoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
//@EnableWebMvc
public class SecurityConfig {

  private final SavedRequestAwareAuthenticationSuccessHandler successHandler;

    @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.oauth2Login(
            oauth2 ->
                oauth2.authorizationEndpoint(
                    endpoint -> endpoint.baseUri("/auth/login"))
                        .successHandler(successHandler)
                    // TODO .failureHandler() 구현하기
            )

        .cors(cors -> cors.configurationSource(this.configurationSource())) // cors 설정
        .csrf(csrf -> csrf.disable()) // csrf설정 비활성화
        .formLogin(form -> form.disable()) // form login 비활성화
        .httpBasic(basic -> basic.disable()) // http basic 비활성화
        .authorizeHttpRequests(auth -> auth
                .requestMatchers("/test/data").authenticated()
                .anyRequest().permitAll()); // 어떤 요청이든 허용
    return http.build();
  }

  // http://localhost:8080/login/oauth2/code/google
  // cors관련 설정
  // https://docs.spring.io/spring-security/reference/servlet/integrations/cors.html
  CorsConfigurationSource configurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOriginPatterns(Arrays.asList("*")); // 모든 요청을 허용한다.
    configuration.setAllowedMethods(
        Arrays.asList("GET", "POST", "OPTIONS", "PUT", "DELETE", "PATCH")); // 허용 메소드 / 모든 메소드 허용
    configuration.setAllowCredentials(true); // dev만
    configuration.setAllowedHeaders(Arrays.asList("*")); // 모든 헤더 허용.
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration); // 모든 요청 허용
    return source;
  }
}
