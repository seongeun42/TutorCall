package com.potato.TutorCall.tutorcall.repository;

import com.potato.TutorCall.tutorcall.domain.RequestCall;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RequestCallRepository extends CrudRepository<RequestCall, UUID> {}
