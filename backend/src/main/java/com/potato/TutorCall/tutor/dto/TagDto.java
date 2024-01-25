package com.potato.TutorCall.tutor.dto;

import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.tutor.domain.enums.SchoolType;
import lombok.Data;

@Data
public class TagDto {

    private Long id;
    private String subject;
    private SchoolType level;
    private int grade;

    public TagDto(Tag tag) {
        this.id = tag.getId();
        this.subject = tag.getSubject();
        this.level = tag.getLevel();
        this.grade = tag.getGrade();
    }

}
