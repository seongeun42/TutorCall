package com.potato.TutorCall.mypage.dto;

import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.tutor.domain.Tutor;
import com.potato.TutorCall.tutor.domain.TutorTag;
import com.potato.TutorCall.tutor.domain.enums.SchoolType;
import lombok.Data;
import lombok.Getter;

import javax.print.attribute.standard.SheetCollate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class TutorProfileDto {
    private final String introduction;
    private final Integer reliability;
    private final Double mannerRate;
    private final Double communicationRate;
    private final Double professionalismRate;
    private final List<TagDto> tags;

    public TutorProfileDto(Tutor tutor, List<TutorTag> tutorTagList) {
        this.introduction = tutor.getIntroduction();
        this.reliability = tutor.getReliablity();
        this.mannerRate = tutor.getMannerRate();
        this.communicationRate = tutor.getCommunicationRate();
        this.professionalismRate = tutor.getProfessionalismRate();

        tags = new ArrayList<>();
        for(TutorTag tutorTag : tutorTagList) {
            tags.add(new TagDto(tutorTag.getTag()));
        }
    }

    @Data
    static class TagDto {
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

}
