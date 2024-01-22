package com.potato.TutorCall.notice.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Faq {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    private String answer;

}
