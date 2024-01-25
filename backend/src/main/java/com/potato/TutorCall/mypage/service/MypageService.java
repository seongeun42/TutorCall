package com.potato.TutorCall.mypage.service;

import com.potato.TutorCall.exception.customException.NotFoundException;
import com.potato.TutorCall.mypage.dto.res.MyPageProfileResDto;
import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.tutor.domain.Tutor;
import com.potato.TutorCall.tutor.domain.TutorTag;
import com.potato.TutorCall.tutor.repository.TutorRepository;
import com.potato.TutorCall.tutor.repository.TutorTagRepository;
import com.potato.TutorCall.tutor.service.TutorService;
import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.domain.enums.RoleType;
import com.potato.TutorCall.user.repository.UserRepository;
import com.potato.TutorCall.user.service.UserService;
import java.util.List;
import java.util.Optional;
import javax.naming.AuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MypageService {

  private final UserRepository userRepository;
  private final TutorService tutorService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public MyPageProfileResDto getUserProfile(Long id) {
    User currentUser =
        userRepository.findById(id).orElseThrow(() -> new NotFoundException("사용자 정보가 없습니다."));

        MyPageProfileResDto userInfo = null;
        if (currentUser.getRole() == RoleType.USER)
            userInfo = MyPageProfileResDto.builder()
                    .user(currentUser)
                    .build();
        else {
            Tutor tutor = tutorService.findById(currentUser.getId());
            List<Tag> tags = tutorService.getTutorTags(tutor);
            userInfo = MyPageProfileResDto.builder()
                    .user(currentUser)
                    .tutor(tutor)
                    .build();
        }

        return userInfo;
    }

    return userInfo;
  }

  @Transactional
  public void updateProfile(Long id, String profile) {
    User currentUser =
        userRepository.findById(id).orElseThrow(() -> new NotFoundException("사용자 정보가 없습니다."));

    currentUser.changeProfile(profile);
  }

  @Transactional
  public void updateNickname(Long id, String nickname) {
    User currentUser =
        userRepository.findById(id).orElseThrow(() -> new NotFoundException("사용자 정보가 없습니다."));

    currentUser.changeNickname(nickname);
  }

  @Transactional
  public void updatePassword(Long id, String password, String newPassword)
      throws AuthenticationException {
    User currentUser =
        userRepository.findById(id).orElseThrow(() -> new NotFoundException("사용자 정보가 없습니다"));
    // 비밀번호 검증
    if (!bCryptPasswordEncoder.matches(password, currentUser.getPassword())) {
      throw new AuthenticationException("기존 비밀번호가 일치하지 않습니다.");
    }

    currentUser.changePassword(bCryptPasswordEncoder.encode(newPassword));
  }
}
