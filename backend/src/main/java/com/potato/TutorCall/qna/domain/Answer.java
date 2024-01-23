package com.potato.TutorCall.qna.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.potato.TutorCall.tutor.domain.Tutor;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
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




    // 생성자
    @Builder
    public Answer(Tutor tutor, Question question, String content) {
        this.tutor = tutor;
        this.question = question;
        this.content = content;
    }




    // 비즈니스 로직
    /**
     * 답변 내용 변경
     *
     * @param content 수정한 내용
     */
    public void changeContent(String content) {
        this.content = content;
    }

    /**
     * 채택된 답변으로 변경
     */
    public void beChosen() {
        isChosen = true;
    }

    /**
     * 답변 삭제
     */
    public void deleted() {
        isDelete = true;
    }

}
