package com.potato.TutorCall.chat.domain;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class ChatParticipants implements Serializable {
  @Id
  private String id;

  private Long userId;

  private String chatroomId;

  private Timestamp lastVisited = Timestamp.valueOf(LocalDateTime.now());

  @Builder
  public ChatParticipants(String id, Long userId, String chatroomId) {
    this.id = id;
    this.userId = userId;
    this.chatroomId = chatroomId;
  }
}
