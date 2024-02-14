package com.potato.TutorCall.chat.domain;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Data
public class ChatParticipants {
  @Id
  String id;

  Long userId;

  String chatroomId;

  LocalDateTime lastVisited = LocalDateTime.now();

  @Builder
  public ChatParticipants(String id, Long userId, String chatroomId) {
    this.id = id;
    this.userId = userId;
    this.chatroomId = chatroomId;
  }
}
