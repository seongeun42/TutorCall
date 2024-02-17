package com.potato.TutorCall.chat.repository.chatroom;

import com.potato.TutorCall.chat.domain.Chatroom;
import com.potato.TutorCall.chat.domain.enums.ChatroomType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

// See also: https://habeebcycle.medium.com/reactive-api-service-using-spring-webflux-and-reactive-data-redis-47ad2467de1d
public interface ChatroomRepository extends CrudRepository<Chatroom, String> {
  Optional<Chatroom> findByIdAndType(String id, ChatroomType type);
}
