package com.potato.TutorCall.qna.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.potato.TutorCall.qna.domain.Question;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
public class CommonResponseDto {

  Question question;
  Page<Question> questions;
  String message;
  LocalDate timestamp;
  Long questionId;
  Long answerId;
}
