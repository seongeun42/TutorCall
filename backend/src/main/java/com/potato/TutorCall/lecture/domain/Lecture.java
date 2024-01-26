package com.potato.TutorCall.lecture.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.potato.TutorCall.review.domain.Review;
import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.tutor.domain.Tutor;
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
public class Lecture {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  private Tutor tutor;

  private String promotionTitle;

  private String promotionContent;

  private boolean promotionState;

  @CreatedDate private LocalDateTime promotionCreatedAt;

  private LocalDateTime promotionDue;

  private int maxParticipants;

  private int participants;

  private String liveUrl;

  private String password;

  private boolean lectureState;

  private int price;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  private Tag tag;

  private LocalDateTime lectureStartAt;

  private LocalDateTime lectureEndAt;

  private boolean isDelete;

  // 양방향 연관 관계
  @JsonManagedReference
  @OneToMany(mappedBy = "lecture", fetch = FetchType.LAZY)
  private List<LectureParticipant> participantList = new ArrayList<>();

  @JsonManagedReference
  @OneToMany(mappedBy = "lecture", fetch = FetchType.LAZY)
  private List<Review> reviewList = new ArrayList<>();

  // 생성자
  @Builder
  public Lecture(
      Tutor tutor,
      String promotionTitle,
      String promotionContent,
      LocalDateTime promotionDue,
      int maxParticipants,
      int price,
      Tag tag) {
    this.tutor = tutor;
    this.promotionTitle = promotionTitle;
    this.promotionContent = promotionContent;
    this.promotionDue = promotionDue;
    this.maxParticipants = maxParticipants;
    this.price = price;
    this.tag = tag;
  }

  // 비즈니스 로직
  /**
   * 모집글 제목 변경
   *
   * @param promotionTitle 변경할 제목
   */
  public void changePromotionTitle(String promotionTitle) {
    this.promotionTitle = promotionTitle;
  }

  /**
   * 모집글 내용 변경
   *
   * @param promotionContent 변경된 모집글
   */
  public void changePromotionContent(String promotionContent) {
    this.promotionContent = promotionContent;
  }

  /**
   * 모집 상태 변경
   *
   * @param promotionState 모집 상태
   */
  public void changePromotionState(boolean promotionState) {
    this.promotionState = promotionState;
  }

  /**
   * 과외 모집 마감일 설정
   *
   * @param promotionDue 모집 마감일
   */
  public void changePromotionDue(LocalDateTime promotionDue) {
    this.promotionDue = promotionDue;
  }

  /**
   * 과외 최대 참가자 수 변경
   *
   * @param maxParticipants 최대 참가자 수
   */
  public void changeMaxParticipants(int maxParticipants) {
    this.maxParticipants = maxParticipants;
  }

  /**
   * 과외 참가자 증감
   *
   * @param amount 신청 시 1, 취소 시 -1
   */
  public void changeParticipants(int amount) {
    this.participants += amount;
  }

  /**
   * 과외룸 live url 변경
   *
   * @param liveUrl live url
   */
  public void changeLiveUrl(String liveUrl) {
    this.liveUrl = liveUrl;
  }

  /**
   * 과외룸 입장 비밀번호 변경
   *
   * @param password 입장 비밀번호
   */
  public void changePassword(String password) {
    this.password = password;
  }

  /**
   * 과외 라이브 진행 상태 변경
   *
   * @param lectureState 과외 진행 상태
   */
  public void changeLectureState(boolean lectureState) {
    this.lectureState = lectureState;
  }

  /**
   * 과외비 변경
   *
   * @param price 새로운 과외비
   */
  public void changePrice(int price) {
    this.price = price;
  }

  /**
   * 과외 태그 수정
   *
   * @param tag 새로운 tag
   */
  public void changeTag(Tag tag) {
    this.tag = tag;
  }

  /**
   * 과외 시작일 설정
   *
   * @param lectureStartAt 과외 시작일
   */
  public void updateLectureStartAt(LocalDateTime lectureStartAt) {
    this.lectureStartAt = lectureStartAt;
  }

  /**
   * 과외 종료일 설정
   *
   * @param lectureEndAt 과외 종료일
   */
  public void updateLectureEndAt(LocalDateTime lectureEndAt) {
    this.lectureEndAt = lectureEndAt;
  }

  /** 과외 모집 삭제 */
  public void deleted() {
    isDelete = true;
  }
}
