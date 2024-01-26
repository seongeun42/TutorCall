package com.potato.TutorCall.tutor.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TutorTag {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  private Tutor tutor;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  private Tag tag;

  // 생성자
  @Builder
  public TutorTag(Tutor tutor, Tag tag) {
    this.tutor = tutor;
    this.tag = tag;
  }
}
