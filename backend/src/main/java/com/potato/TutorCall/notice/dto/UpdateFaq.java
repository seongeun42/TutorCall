package com.potato.TutorCall.notice.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateFaq {
    private String question;
    private String answer;
}
