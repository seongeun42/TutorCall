package com.potato.TutorCall.user.domain;

import com.potato.TutorCall.user.domain.enums.PointType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class PointHistory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private User user;

    private Long receiver;

    private Long sender;

    private PointType type;

    private String desc;

    private int amount;

    @CreatedDate
    private LocalDateTime createdAt;

}
