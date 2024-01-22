package com.potato.TutorCall.qna.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.potato.TutorCall.lecture.domain.Lecture;
import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Question {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private User writer;

    private String title;

    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    private Tag tag;

    private boolean isEnd;

    private boolean isDelete;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;




    // 양방향 연관 관계
    @JsonManagedReference
    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<Answer> answerList = new ArrayList<>();




    // 생성자
    @Builder
    public Question(User writer, String title, String content, Tag tag) {
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.tag = tag;
    }

}
