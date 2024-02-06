package com.potato.TutorCall.tutor.dto;

import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.tutor.domain.Tutor;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
public class TutorDetailDto {

  private final Long id;
  private final String nickname;
  private final String profile;
  private final String introduction;
  private final Integer reliability;
  private final Double mannerRate;
  private final Double communicationRate;
  private final Double professionalismRate;
  private List<TagDto> tags;

  @Builder
  public TutorDetailDto(Tutor tutor, List<Tag> tagList) {
    this.id = tutor.getId();
    this.nickname = tutor.getUser().getNickname();
    this.profile = tutor.getUser().getProfile();
    this.introduction = tutor.getIntroduction();
    this.reliability = tutor.getReliablity();
    this.mannerRate = tutor.getMannerRate();
    this.communicationRate = tutor.getCommunicationRate();
    this.professionalismRate = tutor.getProfessionalismRate();
    if (tagList != null) {
      tags = new ArrayList<>();
      for (Tag tag : tagList) {
        tags.add(new TagDto(tag));
      }
    }
  }

}
