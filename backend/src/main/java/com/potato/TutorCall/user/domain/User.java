package com.potato.TutorCall.user.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.potato.TutorCall.chat.domain.ChatParticipant;
import com.potato.TutorCall.inquiry.domain.Inquiry;
import com.potato.TutorCall.lecture.domain.LectureParticipant;
import com.potato.TutorCall.notification.domain.Notification;
import com.potato.TutorCall.payment.domain.PointHistory;
import com.potato.TutorCall.payment.domain.UserCoupon;
import com.potato.TutorCall.qna.domain.Question;
import com.potato.TutorCall.report.domain.Report;
import com.potato.TutorCall.tutorcall.domain.RequestCall;
import com.potato.TutorCall.tutorcall.domain.TutorCall;
import com.potato.TutorCall.user.domain.enums.RoleType;
import com.potato.TutorCall.user.domain.enums.SnsType;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;

  private String nickname;

  private String password;

  @Enumerated(EnumType.STRING)
  private RoleType role;

  private String profile;

  private SnsType sns;

  private int point;

  private LocalDateTime block;

  private boolean existNotification;

  private boolean noPushNotification;

  private boolean unjoin;

  @CreatedDate
  private LocalDateTime joinDate;


  // 양방향 연관 관계
  @JsonManagedReference
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<Inquiry> inquiryList = new ArrayList<>();

  @JsonManagedReference
  @OneToMany(mappedBy = "reporter", fetch = FetchType.LAZY)
  private List<Report> reportList = new ArrayList<>();

  @JsonManagedReference
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<PointHistory> pointHistoryList = new ArrayList<>();


  @JsonManagedReference
  @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
  private List<UserCoupon> userCouponList = new ArrayList<>();

  @JsonManagedReference
  @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY)
  private List<Notification> notificationList = new ArrayList<>();

  @JsonManagedReference
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<LectureParticipant> rectureList = new ArrayList<>();

  @JsonManagedReference
  @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY)
  private List<Question> questionList = new ArrayList<>();

  @JsonManagedReference
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<TutorCall> tutorCallList = new ArrayList<>();

  @JsonManagedReference
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<ChatParticipant> chatroomList = new ArrayList<>();

  @JsonManagedReference
  @OneToMany(mappedBy = "caller", fetch = FetchType.LAZY)
  private List<RequestCall> requestCallList = new ArrayList<>();

  // 생성자
  @Builder
  public User(
      String email,
      String nickname,
      String password,
      RoleType role,
      String profile,
      SnsType sns,
      int point) {
    this.email = email;
    this.nickname = nickname;
    this.password = password;
    this.role = role;
    this.profile = profile;
    this.sns = sns;
    this.point = point;
  }

  // 비즈니스 로직
  /**
   * 닉네임 변경 함수
   *
   * @param nickname 새로운 닉네임
   */
  public void changeNickname(String nickname) {
    this.nickname = nickname;
  }

  /**
   * 비밀번호 변경
   *
   * @param password 새로운 비밀번호
   */
  public void changePassword(String password) {
    this.password = password;
  }

  /**
   * 프로필 사진 변경
   *
   * @param profile 새로운 프로필 url
   */
  public void changeProfile(String profile) {
    this.profile = profile;
  }

  /**
   * 포인트 증감
   *
   * @param point 증감할 포인트 양
   */
  public void changePoint(int point) {
    this.point += point;
  }

  /**
   * 차단 만료 기일 설정
   *
   * @param block 차단 만료 기일
   */
  public void setBlock(LocalDateTime block) {
    this.block = block;
  }

  /**
   * 새로운 알림 존재 여부 상태 변경
   *
   * @param existNotification 새로운 알림 존재 여부
   */
  public void changeExistNotification(boolean existNotification) {
    this.existNotification = existNotification;
  }

  /**
   * 푸시 알림 수신 여부 변경
   *
   * @param noPushNotification 푸시 알림
   */
  public void changeNoPushNotification(boolean noPushNotification) {
      this.noPushNotification = noPushNotification;
  }

  /** 탈퇴 처리 */
  public void unjoin() {
    this.unjoin = true;
  }
}
