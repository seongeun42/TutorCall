package com.potato.TutorCall.payment.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.potato.TutorCall.user.domain.User;
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
public class UserCoupon {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Coupon coupon;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    private boolean used;

    @CreatedDate
    private LocalDateTime createdAt;

    private LocalDateTime expiredAt;




    // 생성자
    @Builder
    public UserCoupon(Coupon coupon, User owner, LocalDateTime expiredAt) {
        this.coupon = coupon;
        this.owner = owner;
        this.expiredAt = expiredAt;
    }
    
    
    
    
    // 비즈니스 로직
    /**
     *  사용된 쿠폰으로 변경
     */
    public void beUsed() {
        this.used = true;
    }
    
}
