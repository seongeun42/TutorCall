package com.potato.TutorCall.mypage.dto.res;

import com.potato.TutorCall.review.domain.Review;
import com.potato.TutorCall.tutorcall.domain.TutorCall;
import com.potato.TutorCall.user.dto.UserSimpleDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MyTutorCallResDto {
  Long tutoringId;
  UserSimpleDto tutor;
  UserSimpleDto user;
  String problem;
  String replayVideo;
  LocalDateTime createAt;
  String liveUrl;
  Boolean liveState;
  Integer price;
  ReviewInfoDto review;

  public MyTutorCallResDto(TutorCall tutorCall) {
    this.tutoringId = tutorCall.getId();
    this.tutor = UserSimpleDto.builder()
            .user(tutorCall.getTutor().getUser())
            .build();
    this.user = UserSimpleDto.builder().user(tutorCall.getUser()).build();
    this.problem = tutorCall.getProblemContent();
    this.replayVideo = tutorCall.getReplayVideo();
    this.createAt = tutorCall.getCreatedAt();
    this.liveUrl = tutorCall.getLiveUrl();
    this.liveState = tutorCall.isLiveState();
    this.price = tutorCall.getPrice();

    Review tutorCallReview = tutorCall.getReview();
    this.review = ReviewInfoDto.builder()
            .mannerRate(tutorCallReview.getMannerRate())
            .communicationRate(tutorCallReview.getCommunicationRate())
            .professionalismRate(tutorCallReview.getProfessionalismRate())
            .content(tutorCallReview.getContent())
            .build();
  }

  @Getter
  @Builder
  class ReviewInfoDto{
    Integer mannerRate;
    Integer communicationRate;
    Integer professionalismRate;
    String content;
  }

}
