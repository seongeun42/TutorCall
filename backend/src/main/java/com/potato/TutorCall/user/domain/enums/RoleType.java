package com.potato.TutorCall.user.domain.enums;

import lombok.Getter;

@Getter
public enum RoleType {

    USER("ROLE_USER"), TUTOR("ROLE_TUTOR"), ADMIN("ROLE_ADMIN"), UNJOIN("ROLE_UNJOIN");

    private final String value;

    RoleType(String value) {
        this.value = value;
    }

}
