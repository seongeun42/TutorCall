package com.potato.TutorCall.mypage.controller;

import com.potato.TutorCall.auth.SessionKey;
import com.potato.TutorCall.auth.dto.UserSessionDto;
import com.potato.TutorCall.exception.customException.ForbiddenException;
import com.potato.TutorCall.mypage.dto.req.*;
import com.potato.TutorCall.mypage.dto.res.MyLectureListResDto;
import com.potato.TutorCall.mypage.dto.res.MyPageProfileResDto;
import com.potato.TutorCall.mypage.dto.res.ProfileUpdateResDto;
import com.potato.TutorCall.mypage.dto.res.UpdateSuccessResDto;
import com.potato.TutorCall.mypage.service.MypageService;
import com.potato.TutorCall.user.domain.enums.RoleType;
import javax.naming.AuthenticationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;

/** 마이페이지 기능에 대한 컨트롤러 */
@Slf4j
@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {

  private final MypageService mypageService;

  /**
   * 내 정보 조회
   *
   * @return
   */
  @GetMapping
  public ResponseEntity<?> getMyProfile(
      @SessionAttribute(name = SessionKey.USER) UserSessionDto userSession) {
    MyPageProfileResDto myProfile = mypageService.getUserProfile(userSession.getId());

    return ResponseEntity.ok(myProfile);
  }

  /**
   * 프로필 사진 변경
   *
   * @return
   */
  @PatchMapping("/profile")
  @ResponseBody
  public ResponseEntity<?> updateProfileImage(
      @SessionAttribute(name = SessionKey.USER) UserSessionDto userSession,
      MultipartRequest multipartRequest) throws IOException {

    MultipartFile multipartFile = multipartRequest.getFile("profile");
    String url = mypageService.updateProfile(userSession.getId(), multipartFile);

    return ResponseEntity.ok(
        ProfileUpdateResDto
            .builder()
            .message("프로필 사진이 변경되었습니다.")
            .url(url)
            .build());
  }

  /**
   * 닉네임 수정
   *
   * @return
   */
  @PatchMapping("/nickname")
  public ResponseEntity<?> updateNickname(
      @SessionAttribute(name = SessionKey.USER) UserSessionDto userSession,
      @RequestBody NicknameUpdateReqDto newNickname) {
    mypageService.updateNickname(userSession.getId(), newNickname.getNickname());

    return ResponseEntity.ok(new UpdateSuccessResDto("닉네임이 변경되었습니다."));
  }

  /**
   * 비밀번호 변경
   *
   * @return
   */
  @PatchMapping("/password")
  public ResponseEntity<?> updatePassword(
      @SessionAttribute(name = SessionKey.USER) UserSessionDto userSession,
      @RequestBody PasswordUpdateReqDto newPasswordReq)
      throws AuthenticationException {
    mypageService.updatePassword(
        userSession.getId(), newPasswordReq.getPassword(), newPasswordReq.getNewPassword());

    return ResponseEntity.ok(new UpdateSuccessResDto("비밀번호가 변경되었습니다."));
  }

  /**
   * 알림 설정 변경
   *
   * @return
   */
  @PatchMapping("/notification")
  public ResponseEntity<?> updateNotificationConfig(
      @SessionAttribute(name = SessionKey.USER) UserSessionDto userSession,
      @RequestBody NotificationUpdateReqDto notificationUpdateReq) {
    mypageService.updaetNotification(
        userSession.getId(), notificationUpdateReq.getNotificationOption());

    return ResponseEntity.ok(new UpdateSuccessResDto("알림 설정이 변경되었습니다."));
  }

  /**
   * 강의 태그 수정
   *
   * @return
   */
  @PutMapping("/tutor/tag")
  public ResponseEntity<?> updateLectureTag(
      @SessionAttribute(name = SessionKey.USER) UserSessionDto userSession,
      @RequestBody TagUpdateReqDto tagUpdateReq) {
    if (!userSession.getRoleType().equals(RoleType.TUTOR)) {
      throw new ForbiddenException("수정 권한이 없습니다");
    }

    mypageService.updateTag(userSession.getId(), tagUpdateReq.getTags());

    return ResponseEntity.ok(new UpdateSuccessResDto("강의 범위를 수정했습니다."));
  }

  /**
   * 소개글 수정
   *
   * @return
   */
  @PatchMapping("/tutor/intro")
  public ResponseEntity<?> updateIntroduction(
      @SessionAttribute(name = SessionKey.USER) UserSessionDto userSession,
      @RequestBody IntrodutionUpdateReqDto introdutionUpdateReq) {
    if (!userSession.getRoleType().equals(RoleType.TUTOR)) {
      throw new ForbiddenException("수정 권한이 없습니다");
    }

    mypageService.updateIntroduction(userSession.getId(), introdutionUpdateReq.getIntroduction());

    return ResponseEntity.ok(new UpdateSuccessResDto("소개문구를 수정했습니다"));
  }

  /**
   * 내 쿠폰 내역 조회
   *
   * @return
   */
  @GetMapping("/coupon")
  public ResponseEntity<?> getCouponHistory() {
    return ResponseEntity.badRequest().build();
  }

  /**
   * 내 포인트 내역 조회
   *
   * @return
   */
  @GetMapping("/point")
  public ResponseEntity<?> getPointHistory() {
    return ResponseEntity.badRequest().build();
  }

  /**
   * 내 과외 목록 조회(끝났다면 리뷰 작성 여부 포함)
   *
   * @return
   */
  @GetMapping("/lecture")
  public ResponseEntity<?> getLectureList(
      @SessionAttribute(name = SessionKey.USER) UserSessionDto userSession, Pageable pageable) {
    Page<MyLectureListResDto> result = userSession.getRoleType().equals(RoleType.USER) ?
            mypageService.getLectureListUser(userSession.getId(), pageable) :
            mypageService.getLectureListTutor(userSession.getId(), pageable);
    return ResponseEntity.ok(result);
  }

  /**
   * 내 화상강의 내역 조회(리뷰 작성 여부 포함 - 있다면 리뷰까지)
   *
   * @return
   */
  @GetMapping("/tutorCall")
  public ResponseEntity<?> getTutorCallHistory(
      @SessionAttribute(name = SessionKey.USER) UserSessionDto userSession, Pageable pageable) {
    return ResponseEntity.ok(mypageService.getTutorCall(userSession.getId(), pageable));
  }
}
