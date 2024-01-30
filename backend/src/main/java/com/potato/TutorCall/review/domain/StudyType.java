package com.potato.TutorCall.review.domain;


import lombok.Getter;

@Getter
public enum StudyType {
    LECTURE("lecture"),
    TUTORCALL("tutorCall");
    private final String value;

    StudyType(String value) {
        this.value = value;
    }
}
