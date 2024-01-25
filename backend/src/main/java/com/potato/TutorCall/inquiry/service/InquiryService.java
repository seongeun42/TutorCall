package com.potato.TutorCall.inquiry.service;

import com.potato.TutorCall.exception.customException.NotFoundException;
import com.potato.TutorCall.inquiry.domain.Inquiry;
import com.potato.TutorCall.inquiry.dto.InquiryAnswerDto;
import com.potato.TutorCall.inquiry.dto.InquirySaveRequestDto;
import com.potato.TutorCall.inquiry.repository.InquiryRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InquiryService {

  private final InquiryRepository inquiryRepository;

  // 내 문의 조회하기
  @Transactional(readOnly = true)
  public Page<Inquiry> myInquiry(Long userId, Pageable pageable) {
    Page<Inquiry> myInquiries =
        inquiryRepository.findAllByUserIdOrderByCreatedAtDesc(pageable, userId);
    return myInquiries;
  }

  // 문의 등록 하기
  public Long saveInquiry(InquirySaveRequestDto inquiryDto) {
    Inquiry saveInquiry =
        Inquiry.builder()
            //                .user
            //                        .user(new User((long)Math.floor(Math.random() * 11),"1", "1",
            // "1", RoleType.USER, "1", SnsType.KAKAO, 7))
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
}
