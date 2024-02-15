package com.potato.TutorCall.chat.repository.chatroom;

import com.potato.TutorCall.chat.domain.Chatroom;
import org.springframework.data.repository.CrudRepository;

// See also: https://habeebcycle.medium.com/reactive-api-service-using-spring-webflux-and-reactive-data-redis-47ad2467de1d
public interface ChatroomRepository extends CrudRepository<Chatroom, String> {
}
