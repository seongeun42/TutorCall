package com.potato.TutorCall.auth.service;

import com.potato.TutorCall.auth.dto.UserSessionDto;
import com.potato.TutorCall.auth.dto.request.AuthLoginRequestDto;
import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.repository.UserRepository;
import com.potato.TutorCall.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import java.security.InvalidKeyException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepository;
  private final UserService userService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public User login(AuthLoginRequestDto authLoginRequestDto)
      throws DuplicateKeyException, InvalidKeyException {
    User user = userService.findUserByEmail(authLoginRequestDto.getEmail());

    // 유저 존재하지 않을 경우
    if (user == null) throw new DuplicateKeyException("이미 존재하는 이메일입니다.");

    // 소셜 로그인 이메일일 경우
    if (user.getSns() != null) throw new InvalidKeyException("잘못된 로그인 방법입니다.");

    // 패스워드 매치 결과
    boolean passwordMatch =
        bCryptPasswordEncoder.matches(authLoginRequestDto.getPassword(), user.getPassword());

    // 비밀번호가 틀렸을 시
    if (!passwordMatch) throw new InvalidKeyException("아이디 또는 비밀번호를 확인해주세요.");

    // 유저 정보 리턴
    return user;
  }

  public void saveUserInfoToSession(HttpSession session, String key, User user) {
    session.setAttribute(key, UserSessionDto.of(user));
  }

  public void logout(HttpSession session) {
    session.invalidate();
  }
}
