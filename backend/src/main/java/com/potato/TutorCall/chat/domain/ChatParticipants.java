package com.potato.TutorCall.chat.domain;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@RedisHash("chatting_user")
public class ChatParticipants implements Serializable {
  @Id
  private String id;

  @Indexed
  private Long userId;

  @Indexed
  private String chatroomId;

  @Builder
  public ChatParticipants(String id, Long userId, String chatroomId) {
    this.id = id;
    this.userId = userId;
    this.chatroomId = chatroomId;
  }

  @Override
  public String toString() {
    return "ChatParticipants{" +
            "id='" + id + '\'' +
            ", userId=" + userId +
            ", chatroomId='" + chatroomId + '\'' +
            '}';
  }
}
