package com.potato.TutorCall.chat.domain;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@RedisHash("chat")
public class ChatMessage implements Serializable {

  @Id
  private String id;

  private Long senderId;

  @Indexed
  private String chatroomId;

  private String message;

  private LocalDateTime createdAt;

  // 생성자
  @Builder
  public ChatMessage(String id, Long senderId, String chatroomId, String message) {
    this.id = id;
    this.senderId = senderId;
    this.chatroomId = chatroomId;
    this.message = message;
    this.createdAt = LocalDateTime.now();
  }

  @Override
  public String toString() {
    return "ChatMessage{" +
            "id='" + id + '\'' +
            ", senderId=" + senderId +
            ", chatroomId='" + chatroomId + '\'' +
            ", message='" + message + '\'' +
            '}';
  }
}
