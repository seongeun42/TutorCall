package com.potato.TutorCall.tutorcall.domain;

import com.potato.TutorCall.tutor.domain.Tag;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "callReq", timeToLive = 60 * 6)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class RequestCall {

  @Id
  private UUID id;

  private Long caller;

  private String title;

  private String content;

  private Tag tag;

  private int tutorCount;

  private LocalDateTime createdAt;


  // 생성자
  @Builder
  public RequestCall(UUID id, Long caller, String title, String content, Tag tag) {
    this.id = id;
    this.caller = caller;
    this.title = title;
    this.content = content;
    this.tag = tag;
    this.createdAt = LocalDateTime.now();
  }

  // 비즈니스 로직
  /** 요청 수락한 선생님 수 */
  public void increaseTutorCount() {
    tutorCount++;
  }
}
