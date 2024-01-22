package com.potato.TutorCall.tutorcall.domain;

import com.potato.TutorCall.tutor.domain.Tutor;
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
public class ResponseCall {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Tutor tutor;

    private RequestCall call;

    private int price;

    private boolean matched;

    @CreatedDate
    private LocalDateTime createdAt;

}
