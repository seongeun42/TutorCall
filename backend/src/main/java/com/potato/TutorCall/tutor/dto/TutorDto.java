package com.potato.TutorCall.tutor.dto;

import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.tutor.domain.Tutor;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class TutorDto {

  private final Long id;
  private final String introduction;
  private final Integer reliability;
  private final Double mannerRate;
  private final Double communicationRate;
  private final Double professionalismRate;
  private final List<TagDto> tags;

  public TutorDto(Tutor tutor, List<Tag> tagList) {
    this.id = tutor.getId();
    this.introduction = tutor.getIntroduction();
    this.reliability = tutor.getReliablity();
    this.mannerRate = tutor.getMannerRate();
    this.communicationRate = tutor.getCommunicationRate();
    this.professionalismRate = tutor.getProfessionalismRate();

    tags = new ArrayList<>();
    for (Tag tag : tagList) {
      tags.add(new TagDto(tag));
    }
  }
}
