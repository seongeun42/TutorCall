package com.potato.TutorCall.notice.repository;

import com.potato.TutorCall.notice.domain.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
  Page<Notice> findAllByOrderByIdDesc(Pageable pageable);
}
