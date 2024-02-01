package com.potato.TutorCall.auth.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.potato.TutorCall.auth.dto.request.AuthLoginRequestDto;
import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.domain.enums.SnsType;
import com.potato.TutorCall.user.repository.UserRepository;
import com.potato.TutorCall.user.service.UserService;
import java.security.InvalidKeyException;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

  private AuthService authService;

  @Mock private UserRepository userRepository;
  @Mock private UserService userService;
  @Mock BCryptPasswordEncoder bCryptPasswordEncoder;

  private AuthLoginRequestDto authLoginRequestDto;
  private User user;
  private String email = "penguin_03@naver.com";
  private String password = "1234";

  @BeforeEach
  public void before() {
    this.authService = new AuthService(userService, bCryptPasswordEncoder);
    authLoginRequestDto = new AuthLoginRequestDto();

    authLoginRequestDto.setEmail(email);
    authLoginRequestDto.setPassword(password);

    user = User.builder().email(email).password(password).build();
  }

  @Test
  @DisplayName("이메일, 비밀번호가 같으면 User을 리턴해야 한다.")
  void login() throws DuplicateKeyException, InvalidKeyException, BadRequestException {

    // userService.findUserByEmail("Email")을 했을 경우 User 리턴해야한다.
    User findByEmail = userService.findUserByEmail(authLoginRequestDto.getEmail());
    when(findByEmail).thenReturn(user);

    boolean isMatch =
        bCryptPasswordEncoder.matches(authLoginRequestDto.getPassword(), user.getPassword());
    when(isMatch).thenReturn(true);

    User result = authService.login(authLoginRequestDto);
    assertThat(result).isEqualTo(user);
    assertThat(result.getEmail()).isEqualTo(user.getEmail());
    verify(userService, times(1)).findUserByEmail(authLoginRequestDto.getEmail());
    verify(bCryptPasswordEncoder, times(1))
        .matches(authLoginRequestDto.getPassword(), user.getPassword());
  }

  @Test
  @DisplayName("이메일이 없으면 BadRequestException를 발생히켜야 한다.")
  void loginTest2() throws InvalidKeyException, BadRequestException {

    when(userService.findUserByEmail(authLoginRequestDto.getEmail())).thenReturn(null);

    assertThrows(
        BadRequestException.class,
        () -> {
          authService.login(authLoginRequestDto);
        });
    verify(userService, times(1)).findUserByEmail(authLoginRequestDto.getEmail());
    verify(bCryptPasswordEncoder, times(0)).matches(anyString(), anyString());
  }

  @Test
  @DisplayName("Sns계정 이메일이면 InvalidKeyException 발생히켜야 한다.")
  void loginTest3() throws InvalidKeyException, BadRequestException {
    User user = User.builder().email(email).password(password).sns(SnsType.KAKAO).build();

    User findByEmail = userService.findUserByEmail(authLoginRequestDto.getEmail());
    when(findByEmail).thenReturn(user);

    assertThrows(
        InvalidKeyException.class,
        () -> {
          authService.login(authLoginRequestDto);
        });
    verify(userService, times(1)).findUserByEmail(authLoginRequestDto.getEmail());
    verify(bCryptPasswordEncoder, times(0)).matches(anyString(), anyString());
  }

  @Test
  @DisplayName("패스워드 매치 결과가 다르면 InvalidKeyException 발생히켜야 한다.")
  void loginTest4() throws InvalidKeyException, BadRequestException {
    User findByEmail = userService.findUserByEmail(authLoginRequestDto.getEmail());
    when(findByEmail).thenReturn(user);
    boolean isMatch = bCryptPasswordEncoder.matches(anyString(), anyString());
    when(isMatch).thenReturn(false);

    assertThrows(
        InvalidKeyException.class,
        () -> {
          authService.login(authLoginRequestDto);
        });
    verify(userService, times(1)).findUserByEmail(authLoginRequestDto.getEmail());
    verify(bCryptPasswordEncoder, times(1)).matches(anyString(), anyString());
  }

  //    @Test
  //    @DisplayName("세션에 유저 정보를 저장할 수 있다.")
  //    void saveUserInfoToSession() {
  //        HttpSession mockSession = mock(HttpSession.class);
  //        String key = "any";
  //        when(authService.login(mockSession, key,));
  //
  //    }

  @Test
  void logout() {}
}
