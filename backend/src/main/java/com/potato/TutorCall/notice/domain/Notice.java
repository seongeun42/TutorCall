package com.potato.TutorCall.notice.domain;

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
public class Notice {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 500)
  private String title;

  @Column(length = 2000)
  private String content;

  @CreatedDate private LocalDateTime createdAt;

  // 생성자
  @Builder
  public Notice(String title, String content) {
    this.title = title;
    this.content = content;
  }

  // 비즈니스 로직
  /**
   * 공지사항 제목 수정
   *
   * @param title 새로운 제목
   */
  public void changeTitle(String title) {
    this.title = title;
  }

  /**
   * 공지 내용 수정
   *
   * @param content 새로운 내용
   */
  public void changeContent(String content) {
    this.content = content;
  }
}
