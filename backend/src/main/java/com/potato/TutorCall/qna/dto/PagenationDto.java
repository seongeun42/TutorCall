package com.potato.TutorCall.qna.dto;

import lombok.Data;

@Data
public class PagenationDto {

    private int page;
    private boolean isEnd;
    private String tag;
    private String keyword;
    private int size;

}
