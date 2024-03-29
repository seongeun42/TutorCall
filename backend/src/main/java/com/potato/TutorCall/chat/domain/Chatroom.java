package com.potato.TutorCall.chat.domain;

import com.potato.TutorCall.chat.domain.enums.ChatroomType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@RedisHash("chatroom")
public class Chatroom implements Serializable {

  @Id
  private String id;

  private String name;

  @Indexed
  @Enumerated(EnumType.STRING)
  private ChatroomType type;

  // 생성자
  @Builder
  public Chatroom(String id, String name, ChatroomType type) {
    this.id = id;
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
