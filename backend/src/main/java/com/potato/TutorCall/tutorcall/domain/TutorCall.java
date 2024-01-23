package com.potato.TutorCall.tutorcall.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.potato.TutorCall.review.domain.Review;
import com.potato.TutorCall.tutor.domain.Tutor;
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
public class TutorCall {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Tutor tutor;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    private Review review;

    private String problemContent;

    private String replayVideo;

    private String liveUrl;

    private boolean liveState;

    private int price;

    @CreatedDate
    private LocalDateTime createdAt;




    // 생성자
    @Builder
    public TutorCall(Tutor tutor, User user, Review review, String problemContent, String replayVideo, String liveUrl, boolean liveState, int price) {
        this.tutor = tutor;
        this.user = user;
        this.review = review;
        this.problemContent = problemContent;
        this.replayVideo = replayVideo;
        this.liveUrl = liveUrl;
        this.liveState = liveState;
        this.price = price;
    }

}
