package com.potato.TutorCall.mypage.service;

import com.potato.TutorCall.exception.customException.NotFoundException;
import com.potato.TutorCall.lecture.domain.Lecture;
import com.potato.TutorCall.lecture.service.LectureService;
import com.potato.TutorCall.mypage.dto.MyLectureListDto;
import com.potato.TutorCall.mypage.dto.res.MyLectureListResDto;
import com.potato.TutorCall.mypage.dto.res.MyPageProfileResDto;
import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.tutor.domain.Tutor;
import com.potato.TutorCall.tutor.service.TutorService;
import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.domain.enums.RoleType;
import com.potato.TutorCall.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import javax.naming.AuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MypageService {

  private final UserRepository userRepository;
  private final TutorService tutorService;
  private final LectureService lectureService;
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

  @Transactional(readOnly = true)
  public MyLectureListResDto getLectureList(Long id, Pageable pageable) {
    User currentUser = userRepository.findById(id).orElseThrow(() -> new NotFoundException("사용자 정보가 없습니다"));
    List<MyLectureListDto> lectures = new ArrayList<>();

    // 내 강의 정보 가져오기
    List<Lecture> myLectures = lectureService.findUserLectures(currentUser);
    for(Lecture lecture : myLectures) {
      MyLectureListDto myLecture = new MyLectureListDto();
      myLecture.setLectureInfo(lecture);

      // 선생의 정보 가져오기
      User user = userRepository.findById(lecture.getTutor().getId()).orElseThrow(() -> new NotFoundException("선생님 정보가 없습니다"));
      myLecture.setTutorInfo(user);
      
      // 강의의 태그 정보 가져오기
      myLecture.setTagInfo(lecture.getTag());

      // 리뷰 작성 여부 체크
      myLecture.setReviewInfo(lecture.getReviewList());
      
      lectures.add(myLecture);
    }

    PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
    int start = (int) pageRequest.getOffset();
    int end = Math.min((start + pageRequest.getPageSize()), lectures.size());
    Page<MyLectureListDto> lecturePage = new PageImpl<>(lectures.subList(start, end), pageRequest, lectures.size());

    return new MyLectureListResDto(lecturePage);
  }
}
