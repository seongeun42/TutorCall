package com.potato.TutorCall.report.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.potato.TutorCall.report.domain.QReport;
import com.potato.TutorCall.report.domain.Report;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class CommonResponseDto {
    long reportId;
    Page<Report> reports;
    String message;
}
