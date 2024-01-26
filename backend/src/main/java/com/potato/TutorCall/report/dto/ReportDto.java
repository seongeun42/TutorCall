package com.potato.TutorCall.report.dto;

import com.potato.TutorCall.report.domain.Report;
import com.potato.TutorCall.report.domain.enums.ReportType;
import lombok.Builder;
import lombok.Data;

@Data
public class ReportDto {
    private Long id;
    private Long reporter;
    private Long reported;
    private ReportType type;
    private String content;
    private boolean proceedState;
    private String result;

    @Builder
    public ReportDto(Report report) {
        this.id = report.getId();
        this.reporter = report.getReporter().getId();
        this.reported = report.getReported();
        this.type = report.getType();
        this.content = report.getContent();
        this.proceedState = report.isProceedState();
        this.result = report.getResult();
    }


}
