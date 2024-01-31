package com.potato.TutorCall.mypage.dto;

import com.potato.TutorCall.lecture.domain.Lecture;
import com.potato.TutorCall.review.domain.Review;
import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.tutor.dto.TagDto;
import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.dto.UserSimpleDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class MyLectureListDto {
  Long lectureId;
  UserSimpleDto tutor;
  String promotionTitle;
  Boolean promotionState;
  Boolean lectureState;
  TagDto tag;
  LocalDateTime lectureEndAt;
  Boolean review;

  public void setLectureInfo(Lecture lecture) {
    this.lectureId = lecture.getId();
    this.promotionTitle = lecture.getPromotionTitle();
    this.promotionState = LocalDateTime.now().isAfter(lecture.getPromotionDue())? Boolean.FALSE: Boolean.TRUE;
    this.lectureState = lecture.isLectureState();
    this.lectureEndAt = lecture.getLectureEndAt();
  }

  public void setTutorInfo(User user) {
    this.tutor = UserSimpleDto.builder().user(user).build();
  }

  public void setTagInfo(Tag tag) {
    this.tag = new TagDto(tag);
  }

  public void setReviewInfo(List<Review> reviewList) {
    this.review = reviewList.isEmpty()? Boolean.FALSE: Boolean.TRUE;
  }
}