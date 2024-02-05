package com.potato.TutorCall.report.controller;

import com.potato.TutorCall.auth.SessionKey;
import com.potato.TutorCall.auth.dto.UserSessionDto;
import com.potato.TutorCall.report.dto.ReportForm;
import com.potato.TutorCall.report.dto.ReportListDto;
import com.potato.TutorCall.report.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/report")
@Tag(name = "Report API", description = "Report 등록을 위한 API")
public class ReportController {
  private final ReportService reportService;

  public ReportController(ReportService reportService) {
    this.reportService = reportService;
  }

  @Operation(summary = "유저 -> 유저 신고", description = "user reports user")
  @PostMapping("/user/{userId}")
  public ResponseEntity<?> reportUser(
      @PathVariable("userId") int userId,
      @RequestBody ReportForm reportForm,
      HttpSession httpSession) {
    UserSessionDto userSessionDto = (UserSessionDto) httpSession.getAttribute(SessionKey.USER);
    return ResponseEntity.ok(reportService.reportUser(userSessionDto.getId(), userId, reportForm));
  }

  @Operation(summary = "Question Type 신고", description = "Question 신고")
  @PostMapping("/question/{questionId}")
  public ResponseEntity<?> reportQuestion(
      @PathVariable("questionId") int questionId,
      @RequestBody ReportForm reportForm,
      HttpSession httpSession) {

    UserSessionDto userSessionDto = (UserSessionDto) httpSession.getAttribute(SessionKey.USER);
    return ResponseEntity.ok(
        reportService.reportQuestion(userSessionDto.getId(), questionId, reportForm));
  }

  @Operation(summary = "lecture Type 신고", description = "lecture 신고")
  @PostMapping("/lecture/{lectureId}")
  public ResponseEntity<?> reportLecture(
      @PathVariable("lectureId") int lectureId,
      @RequestBody ReportForm reportForm,
      HttpSession httpSession) {

    UserSessionDto userSessionDto = (UserSessionDto) httpSession.getAttribute(SessionKey.USER);
    return ResponseEntity.ok(
        reportService.reportPromotion(userSessionDto.getId(), lectureId, reportForm));
  }

  @Operation(summary = "comment Type 신고", description = "comment 신고")
  @PostMapping("/answer/{answerId}")
  public ResponseEntity<?> reportAnswer(
      @PathVariable("answerId") int answerId,
      @RequestBody ReportForm reportForm,
      HttpSession httpSession) {
    UserSessionDto userSessionDto = (UserSessionDto) httpSession.getAttribute(SessionKey.USER);
    return ResponseEntity.ok(
        reportService.reportAnswer(userSessionDto.getId(), answerId, reportForm));
  }

  @Operation(summary = "신고 목록 조회", description = "Admin은 신고 목록 조회한다.")
  @GetMapping("/report")
  public ResponseEntity<?> allReportList(
      Pageable pageable, ReportListDto reportListDto, HttpSession httpSession) {
    UserSessionDto userSessionDto = (UserSessionDto) httpSession.getAttribute(SessionKey.USER);
    return ResponseEntity.ok(
        reportService.findAllReport(userSessionDto.getId(), pageable, reportListDto));
  }

  @Operation(summary = "신고 처리", description = "Admin은 신고 처리를 한다.")
  @PatchMapping("/report/{reportId}")
  public ResponseEntity<?> acceptReport(
      @PathVariable("reportId") int reportId, HttpSession httpSession) {
    UserSessionDto userSessionDto = (UserSessionDto) httpSession.getAttribute(SessionKey.USER);
    return ResponseEntity.ok(reportService.acceptReport(userSessionDto.getId(), reportId));
  }
}
