package com.potato.TutorCall.inquiry.repository;

import com.potato.TutorCall.inquiry.domain.Inquiry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

  // 내 문의사항 가져오기
  Page<Inquiry> findAllByUserIdOrderByCreatedAtDesc(Pageable pageable, Long userId);
}
