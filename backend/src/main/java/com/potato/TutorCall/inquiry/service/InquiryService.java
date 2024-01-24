package com.potato.TutorCall.inquiry.service;

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

  //     문의 수정 하기
  //    public InquirySaveRequestDto updateInquiry(Long inquiryId, InquirySaveRequestDto inquiryDto)
  // {
  //        Inquiry updatedInquiry = inquiryRepository.getReferenceById(inquiryId);
  //        updatedInquiry.
  //    }

}
