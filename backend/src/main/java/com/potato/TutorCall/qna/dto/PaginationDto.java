package com.potato.TutorCall.qna.dto;

import lombok.Data;

@Data
public class PaginationDto {

    private boolean isEnd;
    private Long tagId;
    private String keyword;


}
