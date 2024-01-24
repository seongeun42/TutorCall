package com.potato.TutorCall.tutorcall.domain;

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
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class RequestCall {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private User caller;

    private String title;

    private String content;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Tag tag;

    private int tutorCount;

    @CreatedDate
    private LocalDateTime createdAt;




    // 양방향 연관 관계
    @JsonManagedReference
    @OneToMany(mappedBy = "call", fetch = FetchType.LAZY)
    private List<ResponseCall> responseCallList = new ArrayList<>();




    // 생성자
    @Builder
    public RequestCall(User caller, String title, String content, Tag tag) {
        this.caller = caller;
        this.title = title;
        this.content = content;
        this.tag = tag;
    }




    // 비즈니스 로직
    /**
     * 요청 수락한 선생님 수
     */
    public void increaseTutorCount() {
        tutorCount++;
    }

}
