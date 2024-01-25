package com.potato.TutorCall.payment.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.potato.TutorCall.payment.domain.enums.PointType;
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
public class PointHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  private Long receiver;

  private Long sender;

  private PointType type;

    private String description;

  private int amount;

    @CreatedDate
    private LocalDateTime createdAt;




    // 생성자
    @Builder
    public PointHistory(User user, Long receiver, Long sender, PointType type, String description, int amount) {
        this.user = user;
        this.receiver = receiver;
        this.sender = sender;
        this.type = type;
        this.description = description;
        this.amount = amount;
    }
}
