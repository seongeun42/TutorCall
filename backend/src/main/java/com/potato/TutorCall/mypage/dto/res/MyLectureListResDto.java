package com.potato.TutorCall.mypage.dto.res;

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
public class MyLectureListResDto {
  Long lectureId;
  UserSimpleDto tutor;
  String promotionTitle;
  Boolean promotionState;
  Boolean lectureState;
  TagDto tag;
  LocalDateTime lectureEndAt;
  Boolean review = Boolean.FALSE;

  public void setLectureInfo(Lecture lecture) {
    this.lectureId = lecture.getId();
    this.promotionTitle = lecture.getPromotionTitle();
    this.promotionState = lecture.isPromotionState();
    this.lectureState = lecture.isLectureState();
    this.lectureEndAt = lecture.getLectureEndAt();
  }

  public void setTutorInfo(User user) {
    this.tutor = UserSimpleDto.builder().user(user).build();
  }

  public void setTagInfo(Tag tag) {
    this.tag = new TagDto(tag);
  }

  public void setReviewInfo() {
    this.review = Boolean.TRUE;
  }
}