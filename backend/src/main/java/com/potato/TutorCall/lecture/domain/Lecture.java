package com.potato.TutorCall.lecture.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.potato.TutorCall.review.domain.Review;
import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.tutor.domain.Tutor;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Lecture {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Tutor tutor;

    private String promotionTitle;

    private String promotionContent;

    private boolean promotionState;

    @CreatedDate
    private LocalDateTime promotionCreatedAt;

    private LocalDateTime promotionDue;

    private int maxParticipants;

    private int participants;

    private String liveUrl;

    private String password;

    private boolean lectureState;

    private int price;

    @OneToOne(fetch = FetchType.LAZY)
    private Tag tag;

    private LocalDateTime lectureStartAt;

    private LocalDateTime lectureEndAt;

    private boolean isDelete;




    // 양방향 연관 관계
    @JsonManagedReference
    @OneToMany(mappedBy = "lecture", fetch = FetchType.LAZY)
    private List<LectureParticipant> participantList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "lecture", fetch = FetchType.LAZY)
    private List<Review> reviewList = new ArrayList<>();




    // 생성자
    @Builder
    public Lecture(Tutor tutor, String promotionTitle, String promotionContent, LocalDateTime promotionDue, int maxParticipants, int price, Tag tag) {
        this.tutor = tutor;
        this.promotionTitle = promotionTitle;
        this.promotionContent = promotionContent;
        this.promotionDue = promotionDue;
        this.maxParticipants = maxParticipants;
        this.price = price;
        this.tag = tag;
    }

}
