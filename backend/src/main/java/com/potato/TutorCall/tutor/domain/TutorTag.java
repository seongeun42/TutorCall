package com.potato.TutorCall.tutor.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TutorTag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Tutor tutor;

    private Tag tag;

}
