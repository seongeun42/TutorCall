package com.potato.TutorCall.tutor.domain;

import com.potato.TutorCall.tutor.domain.enums.SchoolType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;

    private SchoolType level;

    private int grade;

}
