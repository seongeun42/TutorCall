package com.potato.TutorCall.payment.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Commision {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int grade;

    private double commision;

}
