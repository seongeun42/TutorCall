package com.potato.TutorCall.notification.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Notification {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  private User receiver;

  private String content;

  private boolean isChecked;

  @CreatedDate private LocalDateTime createAt;

  // 생성자
  @Builder
  public Notification(User receiver, String content) {
    this.receiver = receiver;
    this.content = content;
  }

  // 비즈니스 로직
  /** 알림 확인 표시 */
  public void beChecked() {
    isChecked = true;
  }
}
