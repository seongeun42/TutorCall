package com.potato.TutorCall.lecture.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.potato.TutorCall.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureParticipant {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  private Lecture lecture;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  // 생성자
  @Builder
  public LectureParticipant(Lecture lecture, User user) {
    this.lecture = lecture;
    this.user = user;
  }
}
