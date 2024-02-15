package com.potato.TutorCall.qna.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.user.domain.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  private User writer;

  @Column(length = 500)
  private String title;

  @Column(length = 40000)
  private String content;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  private Tag tag;

  @Column(columnDefinition = "boolean default false")
  private boolean isEnd;

  @Column(columnDefinition = "boolean default false")
  private boolean isDelete;

  @CreatedDate private LocalDateTime createdAt;

  @LastModifiedDate private LocalDateTime modifiedAt;

  // 양방향 연관 관계
  @JsonManagedReference
  @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
  private List<Answer> answerList = new ArrayList<>();

  // 생성자
  @Builder
  public Question(User writer, String title, String content, Tag tag) {
    this.writer = writer;
    this.title = title;
    this.content = content;
    this.tag = tag;
  }

  // 비즈니스 로직
  /**
   * 질문 제목 변경
   *
   * @param title 변경 제목
   */
  public void changeTitle(String title) {
    this.title = title;
  }

  /**
   * 질문 내용 변경
   *
   * @param content 변경 내용
   */
  public void changeContent(String content) {
    this.content = content;
  }

  /**
   * 질문 태그 변경
   *
   * @param tag 변경 태그
   */
  public void changeTag(Tag tag) {
    this.tag = tag;
  }

  /** 해결 상태로 변경 */
  public void ended() {
    isEnd = true;
  }

  /** 질문 삭제 */
  public void deleted() {
    isDelete = true;
  }
}
