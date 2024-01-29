package com.potato.TutorCall.user.dto;

import com.potato.TutorCall.user.domain.User;
import lombok.Builder;
import lombok.Data;

@Data
public class UserSimpleDto {

    private final Long id;
    private final String nickname;
    private final String profile;

    @Builder
    public UserSimpleDto(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.profile = user.getProfile();
    }

}
