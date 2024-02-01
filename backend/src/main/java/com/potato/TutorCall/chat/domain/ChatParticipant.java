package com.potato.TutorCall.chat.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.potato.TutorCall.user.domain.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "participant")
public class ChatParticipant {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonBackReference
  @Indexed
  private User user;

  @JsonBackReference
  @Indexed
  private Chatroom chatroom;

  private LocalDateTime lastVisitAt;

  private LocalDateTime lastLeaveAt;

  // 생성자
  @Builder
  public ChatParticipant(User user, Chatroom chatroom) {
    this.user = user;
    this.chatroom = chatroom;
  }

  // 비즈니스 로직
  /**
   * 마지막으로 방문한 시간 갱신
   *
   * @param lastVisitAt 방문한 시간
   */
  public void updateLastVisitAt(LocalDateTime lastVisitAt) {
    this.lastVisitAt = lastVisitAt;
  }

  /**
   * 마지막으로 떠난 시간 갱신
   *
   * @param lastLeaveAt 떠난 시간
   */
  public void updateLastLeaveAt(LocalDateTime lastLeaveAt) {
    this.lastLeaveAt = lastLeaveAt;
  }
}
