package com.potato.TutorCall.inquiry.repository;

import com.potato.TutorCall.inquiry.domain.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

  // 내 문의 조회
  //    Page<Inquiry> myInquiry(InquiryDto inquiryDto);

  // 문의 수정
  //    void updateInquiry();

  // 문의 삭제
  //    void deleteInquiry();

  // 문의 답변 등록
  //    void answerInquiry();
}
