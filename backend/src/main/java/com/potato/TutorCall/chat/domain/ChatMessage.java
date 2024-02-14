package com.potato.TutorCall.chat.domain;

import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage implements Serializable {

  @Id
  private String id;

  private Long senderId;

  private String chatroomId;

  private String message;

  @CreatedDate private Timestamp createdAt;

  // 생성자
  @Builder
  public ChatMessage(String id, Long senderId, String chatroomId, String message) {
    this.id = id;
    this.senderId = senderId;
    this.chatroomId = chatroomId;
    this.message = message;
  }
}
