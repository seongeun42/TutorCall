package com.potato.TutorCall.qna.dto;

import lombok.Data;

@Data
public class SearchFormDto {

  private Boolean isEnd;
  private Long tagId;
  private String keyword;

}
