package com.potato.TutorCall.tutorcall.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class TutorAcceptRequestDto {

    private UUID id;
    private Long tutorId;

}
