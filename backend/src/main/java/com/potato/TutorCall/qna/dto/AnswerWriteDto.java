package com.potato.TutorCall.qna.dto;

import lombok.Data;

@Data
public class AnswerWriteDto {

    private long questionId;
    private String answerContent;
    private long tutorUserId; // 임시... session에서 꺼내와야 함

}
