package com.potato.TutorCall.inquiry.service;

import com.potato.TutorCall.exception.customException.NotFoundException;
import com.potato.TutorCall.inquiry.domain.Inquiry;
import com.potato.TutorCall.inquiry.dto.InquirySaveRequestDto;
import com.potato.TutorCall.inquiry.repository.InquiryRepository;
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
    Inquiry inquiry = inquiryRepository.findById(inquiryId).orElseThrow(() -> new NotFoundException("문의 변경 실패"));
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

  // 유저 한 명 선택하기
  public Inquiry selectOneInquiry(Long inquiryId) {
    return inquiryRepository.getReferenceById(inquiryId);
  }
}
