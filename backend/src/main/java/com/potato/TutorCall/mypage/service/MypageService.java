package com.potato.TutorCall.mypage.service;

import com.potato.TutorCall.exception.customException.NotFoundException;
import com.potato.TutorCall.mypage.dto.res.MyLectureListResDto;
import com.potato.TutorCall.mypage.dto.res.MyPageProfileResDto;
import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.tutor.domain.Tutor;
import com.potato.TutorCall.tutor.service.TutorService;
import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.domain.enums.RoleType;
import com.potato.TutorCall.user.repository.UserRepository;

import java.awt.print.Pageable;
import java.util.List;
import javax.naming.AuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MypageService {

  private final UserRepository userRepository;
  private final TutorService tutorService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public MyPageProfileResDto getUserProfile(Long id) {
    User currentUser =
        userRepository.findById(id).orElseThrow(() -> new NotFoundException("사용자 정보가 없습니다."));

    MyPageProfileResDto userInfo = null;
    if (currentUser.getRole() == RoleType.USER) {
      userInfo = MyPageProfileResDto.builder().user(currentUser).build();
    } else if (currentUser.getRole() == RoleType.TUTOR) {
      Tutor tutor = tutorService.findById(currentUser.getId());
      List<Tag> tags = tutorService.getTutorTags(tutor);
      userInfo = MyPageProfileResDto.builder().user(currentUser).tutor(tutor).tags(tags).build();
    }

    return userInfo;
  }

  public void updateProfile(Long id, String profile) {
    User currentUser =
        userRepository.findById(id).orElseThrow(() -> new NotFoundException("사용자 정보가 없습니다."));

    currentUser.changeProfile(profile);
  }

  public void updateNickname(Long id, String nickname) {
    User currentUser =
        userRepository.findById(id).orElseThrow(() -> new NotFoundException("사용자 정보가 없습니다."));

    currentUser.changeNickname(nickname);
  }

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

  public void updaetNotification(Long id, Boolean notificationOption) {
    User currentUser =
        userRepository.findById(id).orElseThrow(() -> new NotFoundException("사용자 정보가 없습니다"));

    currentUser.changeNoPushNotification(notificationOption);
  }

  public void updateTag(Long id, List<Long> tags) {
    userRepository.findById(id).orElseThrow(() -> new NotFoundException("사용자 정보가 없습니다"));

    tutorService.changeTags(id, tags);
  }

  public void updateIntroduction(Long id, String introduction) {
    userRepository.findById(id).orElseThrow(() -> new NotFoundException("사용자 정보가 없습니다"));

    tutorService.changeIntroduction(id, introduction);
  }

  public MyLectureListResDto getLectureList(Long id, Pageable pageable) {
    return null;
  }
}
