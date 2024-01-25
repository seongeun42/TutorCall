package com.potato.TutorCall.tutorcall.repository;

import com.potato.TutorCall.tutorcall.domain.RequestCall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestCallRepository extends JpaRepository<RequestCall, Long> {}
