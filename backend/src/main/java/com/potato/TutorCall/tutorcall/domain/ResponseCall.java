package com.potato.TutorCall.tutorcall.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "callRes", timeToLive = 80)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ResponseCall {

  @Id
  private UUID id;

  private Long tutor;

  private UUID call;

  private int price;

  private boolean matched;

  private LocalDateTime createdAt;

  // 생성자
  @Builder
  public ResponseCall(UUID id, Long tutor, UUID call, int price) {
    this.id = id;
    this.tutor = tutor;
    this.call = call;
    this.price = price;
    this.createdAt = LocalDateTime.now();
  }

  // 비즈니스 로직
  /** 매칭 상태 변경 */
  public void changeMatched() {
    this.matched = true;
  }
}
