package com.potato.TutorCall.mypage.dto.res;

import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.tutor.domain.Tutor;
import com.potato.TutorCall.tutor.dto.TutorDto;
import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.domain.enums.RoleType;
import lombok.Builder;
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
    private final TutorDto tutor;

    @Builder
    public MyPageProfileResDto(User user, Tutor tutor, List<Tag> tags) {
        this.userId = user.getId();
        this.nickName = user.getNickname();
        this.role = user.getRole();
        this.email = user.getEmail();
        this.point = user.getPoint();
        this.profile = user.getProfile();
        this.joinDate = user.getJoinDate();
        this.tutor = new TutorDto(tutor, tags);
    }

}
