package com.potato.TutorCall.report.dto;

import com.potato.TutorCall.report.domain.enums.ReportType;
import lombok.Data;

@Data
public class ReportListDto {
    ReportType type;
    boolean state;
}
