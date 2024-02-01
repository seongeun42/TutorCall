package com.potato.TutorCall.chat.repository;

import com.potato.TutorCall.chat.domain.Chatroom;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatroomRepository extends ListCrudRepository<Chatroom, Long> {}
