package com.potato.TutorCall.lecture.dto;

import com.potato.TutorCall.lecture.domain.Lecture;
import com.potato.TutorCall.tutor.dto.TagDto;
import com.potato.TutorCall.tutor.dto.TutorDetailDto;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class LecturePromotionResponseDto {

  private final Long id;
  private final String promotionTitle;
  private final String promotionContent;
  private final boolean promotionState;
  private final int maxParticipants;
  private final int participants;
  private final int price;
  private final TagDto tag;
  private final TutorDetailDto tutor;
  private final LocalDateTime promotionCreatedAt;
  private final LocalDateTime promotionDue;
  private final boolean isParticipated;

  public LecturePromotionResponseDto(Lecture lecture, boolean isParticipated) {
    this.id = lecture.getId();
    this.promotionTitle = lecture.getPromotionTitle();
    this.promotionContent = lecture.getPromotionContent();
    this.promotionState = lecture.isPromotionState();
    this.maxParticipants = lecture.getMaxParticipants();
    this.participants = lecture.getParticipants();
    this.price = lecture.getPrice();
    this.tag = new TagDto(lecture.getTag());
    this.tutor = TutorDetailDto.builder().tutor(lecture.getTutor()).build();
    this.promotionCreatedAt = lecture.getPromotionCreatedAt();
    this.promotionDue = lecture.getPromotionDue();
    this.isParticipated = isParticipated;
  }
}
