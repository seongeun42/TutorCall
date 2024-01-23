package com.potato.TutorCall.qna.dto;

import lombok.Data;

@Data
public class PaginationDto {

    private int page;
    private boolean isEnd;
    private long tagId;
    private String keyword;
    private int size;

}
