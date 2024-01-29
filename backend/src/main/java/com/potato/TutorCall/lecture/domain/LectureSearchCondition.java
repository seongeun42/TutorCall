package com.potato.TutorCall.lecture.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LectureSearchCondition {

    private String keyword;
    private Long tag;
    private Boolean state;

}
