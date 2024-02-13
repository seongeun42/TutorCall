package com.potato.TutorCall.tutorcall.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class StudentRequestDto {

    private UUID id;
    private String title;
    private String content;
    private Long tagId;
    private Long userId;

}
