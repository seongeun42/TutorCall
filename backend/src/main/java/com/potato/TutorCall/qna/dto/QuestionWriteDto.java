package com.potato.TutorCall.qna.dto;

import lombok.Data;

@Data
public class QuestionWriteDto {

    private long writer;
    private String questionTitle;
    private String questionContent;
    private long tagId;

}
