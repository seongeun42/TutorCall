package com.potato.TutorCall.mypage.dto.res;

import com.potato.TutorCall.review.domain.Review;
import com.potato.TutorCall.tutorcall.domain.TutorCall;
import com.potato.TutorCall.user.dto.UserSimpleDto;
import java.time.LocalDateTime;
import lombok.Getter;

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
    this.tutor = UserSimpleDto.builder().user(tutorCall.getTutor().getUser()).build();
    this.user = UserSimpleDto.builder().user(tutorCall.getUser()).build();
    this.problem = tutorCall.getProblemContent();
    this.replayVideo = tutorCall.getReplayVideo();
    this.createAt = tutorCall.getCreatedAt();
    this.liveUrl = tutorCall.getLiveUrl();
    this.liveState = tutorCall.isLiveState();
    this.price = tutorCall.getPrice();
  }

  public void setReviewInfo(Review review) {
    this.review =
        new ReviewInfoDto(
            review.getMannerRate(),
            review.getCommunicationRate(),
            review.getProfessionalismRate(),
            review.getContent());
  }

  @Getter
  class ReviewInfoDto {
    Integer mannerRate;
    Integer communicationRate;
    Integer professionalismRate;
    String content;

    ReviewInfoDto(
        Integer mannerRate,
        Integer communicationRate,
        Integer professionalismRate,
        String content) {
      this.mannerRate = mannerRate;
      this.communicationRate = communicationRate;
      this.professionalismRate = professionalismRate;
      this.content = content;
    }
  }
}
