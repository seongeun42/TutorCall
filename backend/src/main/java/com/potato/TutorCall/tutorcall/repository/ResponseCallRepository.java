package com.potato.TutorCall.tutorcall.repository;

import com.potato.TutorCall.tutorcall.domain.ResponseCall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseCallRepository extends JpaRepository<ResponseCall, Long> {}
