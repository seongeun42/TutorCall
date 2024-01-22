package com.potato.TutorCall.tutor.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.potato.TutorCall.inquiry.domain.Inquiry;
import com.potato.TutorCall.lecture.domain.Lecture;
import com.potato.TutorCall.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @MapsId
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    private String introduction;

    private int reliablity;

    private double mannerRate;

    private double communicationRate;

    private double professionalismRate;




    // 양방향 연관 관계
    @JsonManagedReference
    @OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY)
    private List<Lecture> lectureList = new ArrayList<>();

}
