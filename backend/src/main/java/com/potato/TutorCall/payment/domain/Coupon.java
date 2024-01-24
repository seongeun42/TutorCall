package com.potato.TutorCall.payment.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

    private String description;

    private int point;

    private int dueDate;

    private LocalDateTime startAt;

    private LocalDateTime endAt;




    // 생성자
    @Builder
    public Coupon(String code, String name, String description, int point, int dueDate, LocalDateTime startAt, LocalDateTime endAt) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.point = point;
        this.dueDate = dueDate;
        this.startAt = startAt;
        this.endAt = endAt;
    }

}
