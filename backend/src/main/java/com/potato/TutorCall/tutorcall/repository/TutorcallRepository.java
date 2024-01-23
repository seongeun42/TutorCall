package com.potato.TutorCall.tutorcall.repository;

import com.potato.TutorCall.tutorcall.domain.TutorCall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorcallRepository extends JpaRepository<TutorCall, Long> {
}
