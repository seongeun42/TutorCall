package com.potato.TutorCall.mypage.dto.res;

import com.potato.TutorCall.mypage.dto.MyLectureListDto;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class MyLectureListResDto {
 Page<MyLectureListDto> lectures;
}
