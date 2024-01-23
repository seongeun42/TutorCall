package com.potato.TutorCall.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatroomParticipantRepository extends JpaRepository<Object, Long> {
}
