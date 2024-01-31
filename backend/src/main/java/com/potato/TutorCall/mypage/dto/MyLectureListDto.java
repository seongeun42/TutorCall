package com.potato.TutorCall.mypage.dto;

import com.potato.TutorCall.tutor.dto.TagDto;
import com.potato.TutorCall.user.dto.UserSimpleDto;

import java.time.LocalDateTime;

public class MyLectureListDto {
  Long lectureId;
  UserSimpleDto tutor;
  String promotionTitle;
  Boolean lectureState;
  TagDto tag;
  LocalDateTime lectureEndAt;
  Boolean review;
}