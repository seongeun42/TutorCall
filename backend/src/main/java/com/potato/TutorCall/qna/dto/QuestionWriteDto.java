package com.potato.TutorCall.qna.dto;

import lombok.Data;

@Data
public class QuestionWriteDto {

    private Long writerId;
    private String questionTitle;
    private String questionContent;
    private Long tagId;

}
