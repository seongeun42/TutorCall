package com.potato.TutorCall.tutorcall.repository;

import com.potato.TutorCall.tutorcall.domain.ResponseCall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ResponseCallRepository extends CrudRepository<ResponseCall, UUID> {}
