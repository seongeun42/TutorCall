package com.potato.TutorCall.chat.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.potato.TutorCall.chat.domain.enums.ChatroomType;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
public class Chatroom {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private ChatroomType type;

  @CreatedDate private LocalDateTime createdAt;

  // 양방향 연관 관계
  @JsonManagedReference
  @OneToMany(mappedBy = "chatroom", fetch = FetchType.LAZY)
  private List<ChatParticipant> participantList = new ArrayList<>();

  @JsonManagedReference
  @OneToMany(mappedBy = "chatroom", fetch = FetchType.LAZY)
  private List<ChatMessage> messageList = new ArrayList<>();

  // 생성자
  @Builder
  public Chatroom(String name, ChatroomType type) {
    this.name = name;
    this.type = type;
  }

  // 비즈니스 로직
  /**
   * 채팅방 명 변경
   *
   * @param name 새로운 채팅방명
   */
  public void changeName(String name) {
    this.name = name;
  }
}
