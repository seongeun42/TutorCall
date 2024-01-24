package com.potato.TutorCall.mypage.dto;

import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.tutor.domain.Tutor;
import com.potato.TutorCall.tutor.domain.TutorTag;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TutorProfileDto {
    private final String introduction;
    private final Integer reliability;
    private final Double mannerRate;
    private final Double communicationRate;
    private final Double professionalismRate;
    private final List<Tag> tags;

    public TutorProfileDto(Tutor tutor, List<TutorTag> tutorTagList) {
        this.introduction = tutor.getIntroduction();
        this.reliability = tutor.getReliablity();
        this.mannerRate = tutor.getMannerRate();
        this.communicationRate = tutor.getCommunicationRate();
        this.professionalismRate = tutor.getProfessionalismRate();

        tags = new ArrayList<>();
        for(TutorTag tutorTag : tutorTagList) {
            tags.add(tutorTag.getTag());
        }
    }
}
