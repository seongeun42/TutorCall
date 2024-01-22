package com.potato.TutorCall.qna.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.potato.TutorCall.tutor.domain.Tutor;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Answer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Tutor tutor;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    private String content;

    private boolean isChosen;

    private boolean isDelete;

    @CreatedDate
    private LocalDateTime createAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

}
