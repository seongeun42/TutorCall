package com.potato.TutorCall.tutor.domain;

import com.potato.TutorCall.tutor.domain.enums.SchoolType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {

  @Id
  private Long id;

  private String subject;

  private SchoolType level;

  private int grade;

  // 생성자
  @Builder
  public Tag(Long id, String subject, SchoolType level, int grade) {
    this.id = id;
    this.subject = subject;
    this.level = level;
    this.grade = grade;
  }
}
