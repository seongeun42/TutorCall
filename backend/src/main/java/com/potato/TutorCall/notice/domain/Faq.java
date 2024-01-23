package com.potato.TutorCall.notice.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Faq {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    private String answer;




    // 생성자
    @Builder
    public Faq(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
    
    
    
    
    // 비즈니스 로직
    /**
     * 질문 내용 수정
     * 
     * @param question 새로운 질문
     */
    public void changeQuestion(String question) {
        this.question = question;
    }

    /**
     * 답변 내용 수정
     * 
     * @param answer 새로운 답변
     */
    public void changeAnswer(String answer) {
        this.answer = answer;
    }
    
}
