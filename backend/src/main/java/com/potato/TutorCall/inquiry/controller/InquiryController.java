package com.potato.TutorCall.inquiry.controller;

import com.potato.TutorCall.inquiry.dto.InquiryDto;
import com.potato.TutorCall.inquiry.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inquiry")
@RequiredArgsConstructor
public class InquiryController {

  private final InquiryService inquiryService;

  // 내 문의 조회
  @GetMapping
  public ResponseEntity<?> myInquiry(InquiryDto inquiryDto) {
    return null;
  }

  // 문의 등록
  @PostMapping
  public ResponseEntity<?> createInquiry() {
    return null;
  }

  // 문의 수정
  @PatchMapping("/{inquiryId}")
  public ResponseEntity<?> updateInquiry(@PathVariable Long inquiryId) {
    return null;
  }

  // 문의 삭제
  @DeleteMapping("/{inquiryId}")
  public ResponseEntity<?> deleteInquiry(@PathVariable Long inquiryId) {
    return null;
  }

  // 문의 답변 등록
  @PatchMapping("/answer/{inquiryId}")
  public ResponseEntity<?> answerInquiry(@PathVariable Long inquiryId) {
    return null;
  }
}
