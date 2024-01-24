package com.potato.TutorCall.mypage.controller;

import com.potato.TutorCall.mypage.dto.req.MyPagePaginationDto;
import com.potato.TutorCall.mypage.dto.res.MyPageProfileResDto;
import com.potato.TutorCall.mypage.service.MypageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/** 마이페이지 기능에 대한 컨트롤러 */
@RestController
@RequestMapping("/mypage")
@Slf4j
@RequiredArgsConstructor
public class MyPageController {

  private final MypageService mypageService;

  /**
   * 내 정보 조회
   *
   * @return
   */
  @GetMapping
  public ResponseEntity<?> getMyProfile(@SessionAttribute(name = "user")Long id) {
    Optional<MyPageProfileResDto> myProfile = mypageService.getUserProfile(id);

    if(myProfile.isPresent()) {
      return ResponseEntity.ok(myProfile.get());
    }
    
    return ResponseEntity.badRequest().build();
  }

  /**
   * 프로필 사진 변경
   *
   * @return
   */
  @PatchMapping("/profile")
  public ResponseEntity<?> updateProfileImage() {
    return ResponseEntity.badRequest().build();
  }

  /**
   * 닉네임 수정
   *
   * @return
   */
  @PatchMapping("/nickname")
  public ResponseEntity<?> updateNickname() {
    return ResponseEntity.badRequest().build();
  }

  /**
   * 비밀번호 변경
   *
   * @return
   */
  @PatchMapping("/password")
  public ResponseEntity<?> updatePassword() {
    return ResponseEntity.badRequest().build();
  }

  /**
   * 알림 설정 변경
   *
   * @return
   */
  @PatchMapping("/notification")
  public ResponseEntity<?> updateNotificationConfig() {
    return ResponseEntity.badRequest().build();
  }

  /**
   * 강의 태그 수정
   *
   * @return
   */
  @PutMapping("/tutor/tag")
  public ResponseEntity<?> updateLectureTag() {
    return ResponseEntity.badRequest().build();
  }

  /**
   * 소개글 수정
   *
   * @return
   */
  @PatchMapping("/tutor/intro")
  public ResponseEntity<?> updateIntroduction() {
    return ResponseEntity.badRequest().build();
  }

  /**
   * 내 쿠폰 내역 조회
   *
   * @param paginationOption pageNo(페이지 번호), size(한 페이지의 크기)
   * @return
   */
  @GetMapping("/coupon")
  public ResponseEntity<?> getCouponHistory(MyPagePaginationDto paginationOption) {
    return ResponseEntity.badRequest().build();
  }

  /**
   * 내 포인트 내역 조회
   *
   * @param paginationOption pageNo(페이지 번호), size(한 페이지의 크기)
   * @return
   */
  @GetMapping("/point")
  public ResponseEntity<?> getPointHistory(MyPagePaginationDto paginationOption) {
    return ResponseEntity.badRequest().build();
  }

  /**
   * 내 과외 목록 조히(끝났다면 리뷰 작성 여부 포함)
   *
   * @return
   */
  @GetMapping("/lecture")
  public ResponseEntity<?> getLectureList() {
    return ResponseEntity.badRequest().build();
  }

  /**
   * 내 화상강의 내역 조회(리뷰 작성 여부 포함 - 있다면 리뷰까지)
   *
   * @return
   */
  @GetMapping("/tutorcall")
  public ResponseEntity<?> getTutorcallHistory() {
    return ResponseEntity.badRequest().build();
  }
}
