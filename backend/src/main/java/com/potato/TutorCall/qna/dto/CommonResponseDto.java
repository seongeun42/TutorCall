package com.potato.TutorCall.qna.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.potato.TutorCall.qna.domain.Answer;
import com.potato.TutorCall.qna.domain.Question;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
public class CommonResponseDto {

    Question question;
    Answer answer;
    Page<Question> questions;
    String message;
    LocalDate timestamp;
    Long questionId;

}
