package com.potato.TutorCall.chat.repository;

import com.potato.TutorCall.chat.domain.Chatroom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatroomRepository extends CrudRepository<Chatroom, Long> {}
