package com.potato.TutorCall.payment.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
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

    private String desc;

    private int point;

    private int dueDate;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

}
