package com.potato.TutorCall.inquiry.service;

import com.potato.TutorCall.exception.customException.NotFoundException;
import com.potato.TutorCall.inquiry.domain.Inquiry;
import com.potato.TutorCall.inquiry.dto.InquiryAnswerDto;
import com.potato.TutorCall.inquiry.dto.InquirySaveRequestDto;
import com.potato.TutorCall.inquiry.repository.InquiryRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InquiryService {

  private final InquiryRepository inquiryRepository;

  // 문의 등록 하기
  public Long saveInquiry(InquirySaveRequestDto inquiryDto) {
    Inquiry saveInquiry =
        Inquiry.builder()
            //                .user
            .title(inquiryDto.getTitle())
            .content(inquiryDto.getContent())
            .build();
    inquiryRepository.save(saveInquiry);
    try {
      return saveInquiry.getId();
    } catch (Exception e) {
      return null;
    }
  }

  // 문의 수정 하기
  public Long updateInquiry(Long inquiryId, InquirySaveRequestDto inquiryDto) {
    Inquiry inquiry =
        inquiryRepository.findById(inquiryId).orElseThrow(() -> new NotFoundException("문의 변경 실패"));
    inquiry.changeTitle(inquiryDto.getTitle());
    inquiry.changeContent(inquiryDto.getContent());
    inquiryRepository.save(inquiry);
    return inquiryId;
  }

  // 문의 삭제 하기
  public boolean deleteInquiry(Long inquiryId) {
    Inquiry deletedInquiry = inquiryRepository.getReferenceById(inquiryId);
    if (deletedInquiry.getId() == null) {
      return false;
    }
    inquiryRepository.deleteById(inquiryId);
    return true;
  }

  // 문의에 대한 답변 등록하기 (관리자)
  public boolean answerInquiry(Long inquiryId, InquiryAnswerDto answerDto) {

    // 해당 문의번호에 해당하는 문의가 존재하는지 확인
    Inquiry answeredInquiry =
        inquiryRepository.findById(inquiryId).orElseThrow(() -> new NotFoundException("답변 등록 실패"));

    // 사용자가 관리자 권한을 가졌는지 확인

    // 문의 답변 등록하기
    answeredInquiry.setAnswer(answerDto.getAnswer());
    answeredInquiry.updateAnswerState();
    answeredInquiry.updateAnswerAt(LocalDateTime.now());
    return true;
  }

  // 유저 한 명 선택하기
  public Inquiry selectOneInquiry(Long inquiryId) {
    return inquiryRepository.getReferenceById(inquiryId);
  }
}
