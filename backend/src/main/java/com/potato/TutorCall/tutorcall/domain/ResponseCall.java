package com.potato.TutorCall.tutorcall.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.potato.TutorCall.tutor.domain.Tutor;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
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

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Tutor tutor;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private RequestCall call;

    private int price;

    private boolean matched;

    @CreatedDate
    private LocalDateTime createdAt;




    // 생성자
    @Builder
    public ResponseCall(Tutor tutor, RequestCall call, int price) {
        this.tutor = tutor;
        this.call = call;
        this.price = price;
    }




    // 비즈니스 로직
    /**
     * 매칭 상태 변경
     */
    public void changeMatched() {
        this.matched = true;
    }

}
