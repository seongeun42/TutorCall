package com.potato.TutorCall.tutorcall.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class StudentAcceptRequestDto {

    private UUID reqId;
    private Long tutor;

}
