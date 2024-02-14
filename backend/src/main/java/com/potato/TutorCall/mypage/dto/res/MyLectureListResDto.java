package com.potato.TutorCall.mypage.dto.res;

import com.potato.TutorCall.lecture.domain.Lecture;
import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.tutor.dto.TagDto;
import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.dto.UserSimpleDto;
import java.time.LocalDateTime;
import lombok.Getter;

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

  public MyLectureListResDto(Lecture lecture) {
    this.lectureId = lecture.getId();
    this.promotionTitle = lecture.getPromotionTitle();
    this.promotionState = lecture.isPromotionState();
    this.lectureState = lecture.isLectureState();
    this.lectureEndAt = lecture.getLectureEndAt();
  }

  public MyLectureListResDto(Lecture lecture, User tutor) {
    this.lectureId = lecture.getId();
    this.tutor = UserSimpleDto.builder().user(tutor).build();
    this.promotionTitle = lecture.getPromotionTitle();
    this.promotionState = lecture.isPromotionState();
    this.lectureState = lecture.isLectureState();
    this.tag = new TagDto(lecture.getTag());
    this.lectureEndAt = lecture.getLectureEndAt();
    this.review = false;
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
