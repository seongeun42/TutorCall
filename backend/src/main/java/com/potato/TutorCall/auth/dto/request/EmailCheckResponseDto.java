package com.potato.TutorCall.auth.dto.request;

import lombok.Data;

@Data
public class EmailCheckResponseDto {
    private String email;
    private String code;
}
