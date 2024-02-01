package com.potato.TutorCall.chat.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.potato.TutorCall.user.domain.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "chat")
public class ChatMessage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonBackReference
  private User user;

  @JsonBackReference
  private Chatroom chatroom;

  private String message;

  @CreatedDate private LocalDateTime createdAt;

  // 생성자
  @Builder
  public ChatMessage(User user, Chatroom chatroom, String message) {
    this.user = user;
    this.chatroom = chatroom;
    this.message = message;
  }
}
