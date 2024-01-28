package com.potato.TutorCall.chat.repository;

import com.potato.TutorCall.chat.domain.ChatParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatroomParticipantRepository extends JpaRepository<ChatParticipant, Long> {}
