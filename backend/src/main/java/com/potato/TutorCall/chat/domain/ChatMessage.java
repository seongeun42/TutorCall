package com.potato.TutorCall.chat.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.potato.TutorCall.user.domain.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.redis.core.RedisHash;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage {

  @Id
  private String id;

  private Long senderId;

  private String chatroomId;

  private String message;

  @CreatedDate private LocalDateTime createdAt;

  // 생성자
  @Builder
  public ChatMessage(String id, Long senderId, String chatroomId, String message) {
    this.id = id;
    this.senderId = senderId;
    this.chatroomId = chatroomId;
    this.message = message;
  }
}
