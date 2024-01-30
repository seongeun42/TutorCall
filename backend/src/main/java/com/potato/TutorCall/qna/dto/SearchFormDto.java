package com.potato.TutorCall.qna.dto;

import lombok.Data;
import lombok.ToString;

@Data
public class SearchFormDto {

  private boolean isEnd;
  private Long tagId;
  private String keyword;
}
