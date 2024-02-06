package com.potato.TutorCall.tutor.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.potato.TutorCall.lecture.domain.Lecture;
import com.potato.TutorCall.qna.domain.Answer;
import com.potato.TutorCall.tutorcall.domain.TutorCall;
import com.potato.TutorCall.user.domain.User;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tutor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @MapsId
  @OneToOne(fetch = FetchType.LAZY)
  private User user;

  private String introduction;

  private boolean isActive;

  private int reliablity;

  private double mannerRate;

  private double communicationRate;

  private double professionalismRate;

  // 양방향 연관 관계
  @JsonManagedReference
  @OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY)
  private List<Lecture> lectureList = new ArrayList<>();

  @JsonManagedReference
  @OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY)
  private List<Answer> answerList = new ArrayList<>();

  @JsonManagedReference
  @OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY)
  private List<TutorCall> tutorCallList = new ArrayList<>();

  @JsonManagedReference
  @OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY)
  private List<TutorTag> tutorTagList = new ArrayList<>();

  // 생성자
  @Builder
  public Tutor(User user, String introduction) {
    this.user = user;
    this.introduction = introduction;
  }

  // 비즈니스 로직
  /**
   * 소개글 변경
   *
   * @param introduction 새로운 소개글
   */
  public void changeIntroduction(String introduction) {
    this.introduction = introduction;
  }

  /**
   * 활성화 상태 변경
   *
   * @param active 활성화 상태
   */
  public void changeActiveState(boolean active) {
    isActive = active;
  }

  /**
   * 신뢰도 증감
   *
   * @param amount 증감 양
   */
  public void changeReliablity(int amount) {
    this.reliablity += amount;
  }

  /**
   * 매너 평점 변경
   *
   * @param mannerRate 매너 평점
   */
  public void changeMannerRate(double mannerRate) {
    this.mannerRate = mannerRate;
  }

  /**
   * 전달력 평점 변경
   *
   * @param communicationRate 전달력 평점
   */
  public void changeCommunicationRate(double communicationRate) {
    this.communicationRate = communicationRate;
  }

  /**
   * 전문성 평점 변경
   *
   * @param professionalismRate 전문성 평점
   */
  public void changeProfessionalismRate(double professionalismRate) {
    this.professionalismRate = professionalismRate;
  }
}
