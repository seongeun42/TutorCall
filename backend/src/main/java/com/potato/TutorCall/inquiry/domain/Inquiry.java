package com.potato.TutorCall.inquiry.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.potato.TutorCall.user.domain.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;
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
public class Inquiry {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String title;

    private String content;

    private String answer;

    private boolean answerState;

    @CreatedDate
    private LocalDateTime createdAt;

    private LocalDateTime answerAt;




    // 생성자
    @Builder
    public Inquiry(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
    }




    // 비즈니스 로직

    /**
     * 문의 제목 변경
     * @param title 변경할 제목
     */
    public void changeTitle(String title) {
        this.title = title;
    }

    /**
     * 문의 내용 변경
     * @param content 변경할 내용
     */
    public void changeContent(String content) {
        this.content = content;
    }

    /**
     * 답변 등록
     * @param answer 답변
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * 답변 상태 답변 완료로 변경
     */
    public void updateAnswerState() {
        this.answerState = true;
    }

    /**
     * 답변 달린 시간 기록
     * @param answerAt 답변한 시간
     */
    public void updateAnswerAt(LocalDateTime answerAt) {
        this.answerAt = answerAt;
    }

}
