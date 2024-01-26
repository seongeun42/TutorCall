package com.potato.TutorCall.config;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@ApiResponses(
    value = {
      @ApiResponse(responseCode = "200", description = "성공"),
      @ApiResponse(responseCode = "400", description = "잘못된 요청"),
      @ApiResponse(responseCode = "500", description = "내부 서버 오류")
    })
public @interface CommonResponses {}
