package com.potato.TutorCall.mypage.dto.res;

import com.potato.TutorCall.mypage.dto.TutorProfileDto;
import com.potato.TutorCall.tutor.domain.Tutor;
import com.potato.TutorCall.tutor.domain.TutorTag;
import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.domain.enums.RoleType;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class MyPageProfileResDto {
    private final Long userId;
    private final String nickName;
    private final RoleType role;
    private final String email;
    private final Integer point;
    private final String profile;
    private final LocalDateTime joinDate;
    private TutorProfileDto tutor;

    public MyPageProfileResDto(User user) {
        this.userId = user.getId();
        this.nickName = user.getNickname();
        this.role = user.getRole();
        this.email = user.getEmail();
        this.point = user.getPoint();
        this.profile = user.getProfile();
        this.joinDate = user.getJoinDate();
    }

    public void addTutorInfo(Tutor tutor, List<TutorTag> tutorTagList) {
        this.tutor = new TutorProfileDto(tutor, tutorTagList);
    }
}
