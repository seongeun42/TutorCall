package com.potato.TutorCall.review.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.potato.TutorCall.lecture.domain.Lecture;
import com.potato.TutorCall.tutor.domain.Tutor;
import com.potato.TutorCall.tutorcall.domain.TutorCall;
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
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  private Tutor tutor;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  private User reviewer;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  private Lecture lecture;

  @OneToOne(fetch = FetchType.LAZY)
  private TutorCall tutorCall;

  private int mannerRate;

  private int communicationRate;

  private int professionalismRate;

  @Column(length = 1000)
  private String content;

  private StudyType type;

  @CreatedDate private LocalDateTime createdAt;

  // 생성자
  @Builder
  public Review(
      Tutor tutor,
      User reviewer,
      Lecture lecture,
      TutorCall tutorCall,
      int mannerRate,
      int communicationRate,
      int professionalismRate,
      StudyType studyType,
      String content) {
    this.tutor = tutor;
    this.type = studyType;
    this.reviewer = reviewer;
    this.tutorCall = tutorCall;
    this.lecture = lecture;
    this.mannerRate = mannerRate;
    this.communicationRate = communicationRate;
    this.professionalismRate = professionalismRate;
    this.content = content;
  }

  // 비즈니스 로직
  /**
   * 매너 평점 수정
   *
   * @param mannerRate 매너 평점
   */
  public void changeMannerRate(int mannerRate) {
    this.mannerRate = mannerRate;
  }

  /**
   * 전달력 평점 수정
   *
   * @param communicationRate 전달력 평점
   */
  public void changeCommunicationRate(int communicationRate) {
    this.communicationRate = communicationRate;
  }

  /**
   * 전문성 평점 수정
   *
   * @param professionalismRate 전문성 평점
   */
  public void changeProfessionalismRate(int professionalismRate) {
    this.professionalismRate = professionalismRate;
  }

  /**
   * 평가 내용 수정
   *
   * @param content 평가 내용
   */
  public void changeContent(String content) {
    this.content = content;
  }
}
