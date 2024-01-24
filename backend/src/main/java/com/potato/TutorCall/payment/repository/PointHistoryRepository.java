package com.potato.TutorCall.payment.repository;

import com.potato.TutorCall.payment.domain.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {
}
