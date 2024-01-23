package com.potato.TutorCall.tutor.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.potato.TutorCall.lecture.domain.Lecture;
import com.potato.TutorCall.qna.domain.Answer;
import com.potato.TutorCall.qna.domain.Question;
import com.potato.TutorCall.tutorcall.domain.TutorCall;
import com.potato.TutorCall.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
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
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    private String introduction;

    private boolean isActive;

    private int reliablity;

    private double mannerRate;

    private double communicationRate;

    private double professionalismRate;




    // 양방향 연관 관계
    @JsonManagedReference
    @OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY)
    private List<Lecture> lectureList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY)
    private List<Answer> answerList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY)
    private List<TutorCall> tutorCallList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY)
    private List<TutorTag> tutorTagList = new ArrayList<>();




    // 생성자
    @Builder
    public Tutor(User user, String introduction) {
        this.user = user;
        this.introduction = introduction;
    }

}
