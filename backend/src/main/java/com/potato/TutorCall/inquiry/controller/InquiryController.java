package com.potato.TutorCall.inquiry.controller;

import com.potato.TutorCall.inquiry.dto.*;
import com.potato.TutorCall.inquiry.service.InquiryService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inquiry")
@RequiredArgsConstructor
public class InquiryController {

  private final InquiryService inquiryService;

  // 테스트용
  //  @PostMapping("/test")
  //  public String createInquiry(@RequestBody String text) {
  //    System.out.println("TEST");
  //    return text;
  //  }

  // 내 문의 조회
  @GetMapping
  public ResponseEntity<?> myInquiry(
      @RequestParam(value = "userId") Long userId, Pageable pageable) {
    Page<InquiryDto> myInquiries = inquiryService.myInquiry(userId, pageable).map(InquiryDto::new);
    return new ResponseEntity<>(myInquiries, HttpStatusCode.valueOf(200));
  }

  // 문의 등록
  @PostMapping
  public ResponseEntity<?> saveInquiry(@RequestBody InquirySaveRequestDto inquiryDto) {
    Long savedInquiry = inquiryService.saveInquiry(inquiryDto);

    // 실패인 경우
    if (savedInquiry == null) {
      ErrorMsg err = new ErrorMsg();
      err.setTimeStamp(LocalDateTime.now());
      err.setMessage("문의 생성 실패");
      return new ResponseEntity<ErrorMsg>(err, HttpStatusCode.valueOf(401));
    }

    // 성공인 경우
    SuccessMsg msg = new SuccessMsg();
    msg.setInquiryId(savedInquiry);
    msg.setMessage("문의가 생성되었습니다");
    return new ResponseEntity<SuccessMsg>(msg, HttpStatusCode.valueOf(201));
  }

  // 문의 수정
  @PatchMapping("/{inquiryId}")
  public ResponseEntity<?> updateInquiry(
      @PathVariable Long inquiryId, @RequestBody InquirySaveRequestDto inquiryDto) {
    Long updateRequestDto = inquiryService.updateInquiry(inquiryId, inquiryDto);
    // 실패일 경우는 InquiryService에서 Exception을 통해 처리,
    // 성공인 경우
    SuccessMsg msg = new SuccessMsg();
    msg.setInquiryId(updateRequestDto);
    msg.setMessage("문의가 수정되었습니다");
    return new ResponseEntity<SuccessMsg>(msg, HttpStatusCode.valueOf(200));
  }

  // 문의 삭제
  @DeleteMapping("/{inquiryId}")
  public ResponseEntity<?> deleteInquiry(@PathVariable Long inquiryId) {

    boolean isDeleted = inquiryService.deleteInquiry(inquiryId);
    // 성공인 경우
    if (isDeleted) {
      SuccessMsgContent msg = new SuccessMsgContent();
      msg.setMessage("문의가 삭제되었습니다");
      return new ResponseEntity<SuccessMsgContent>(msg, HttpStatusCode.valueOf(201));
    }

    // 권한이 없는 경우?

    // 삭제 실패인 경우
    ErrorMsg err = new ErrorMsg();
    err.setTimeStamp(LocalDateTime.now());
    err.setMessage("문의 삭제 실패");
    return new ResponseEntity<ErrorMsg>(err, HttpStatusCode.valueOf(400));
  }

  // 문의 답변 등록
  @PatchMapping("/answer/{inquiryId}")
  public ResponseEntity<?> answerInquiry(
      @PathVariable Long inquiryId, @RequestBody InquiryAnswerDto answerDto) {

    boolean isAnswered = inquiryService.answerInquiry(inquiryId, answerDto);
    if (!isAnswered) {
      return null;
    }
    SuccessMsgContent msg = new SuccessMsgContent();
    msg.setMessage("답변 등록이 완료되었습니다.");
    return new ResponseEntity<SuccessMsgContent>(msg, HttpStatusCode.valueOf(200));
  }
}
