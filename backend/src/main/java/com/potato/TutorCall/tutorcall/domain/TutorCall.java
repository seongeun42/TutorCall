package com.potato.TutorCall.tutorcall.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.potato.TutorCall.review.domain.Review;
import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.tutor.domain.Tutor;
import com.potato.TutorCall.user.domain.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;
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
public class TutorCall {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  private Tutor tutor;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  @OneToOne(fetch = FetchType.LAZY)
  private Review review;

  private String problemContent;

  private String replayVideo;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  private Tag tag;

  private String liveUrl;

  private boolean liveState;

  private int price;

  @CreatedDate private LocalDateTime createdAt;

  // 생성자
  @Builder
  public TutorCall(
      Tutor tutor,
      User user,
      Review review,
      String problemContent,
      String replayVideo,
      Tag tag,
      String liveUrl,
      boolean liveState,
      int price) {
    this.tutor = tutor;
    this.user = user;
    this.review = review;
    this.problemContent = problemContent;
    this.replayVideo = replayVideo;
    this.tag = tag;
    this.liveUrl = liveUrl;
    this.liveState = liveState;
    this.price = price;
  }

  // 비즈니스 로직
  /**
   * 다시보기 영상 url 설정
   *
   * @param replayVideo 영상 url
   */
  public void setReplayVideo(String replayVideo) {
    this.replayVideo = replayVideo;
  }

  /**
   * 강의룸 url 설정
   *
   * @param liveUrl 강의룸 url
   */
  public void setLiveUrl(String liveUrl) {
    this.liveUrl = liveUrl;
  }

  /**
   * 강의 진행 상태 변경
   *
   * @param liveState 강의 진행 상태
   */
  public void changeLiveState(boolean liveState) {
    this.liveState = liveState;
  }

  /**
   * 질문 태그 변경
   *
   * @param tag 변경 태그
   */
  public void changeTag(Tag tag) {
    this.tag = tag;
  }
}
