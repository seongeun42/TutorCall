package com.potato.TutorCall.lecture.domain;

import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.tutor.domain.Tutor;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Lecture {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    private Tag tag;

    private LocalDateTime lectureStartAt;

    private LocalDateTime lectureEndAt;

    private boolean isDelete;

}
