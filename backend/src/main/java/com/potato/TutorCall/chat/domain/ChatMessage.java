package com.potato.TutorCall.chat.domain;

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
public class ChatMessage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
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
