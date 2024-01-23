package com.potato.TutorCall.payment.repository;

import com.potato.TutorCall.payment.domain.Commision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommisionRepository extends JpaRepository<Commision, Long> {
}
