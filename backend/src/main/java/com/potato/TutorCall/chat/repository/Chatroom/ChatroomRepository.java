package com.potato.TutorCall.chat.repository.Chatroom;

import com.potato.TutorCall.chat.domain.Chatroom;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

// See also: https://habeebcycle.medium.com/reactive-api-service-using-spring-webflux-and-reactive-data-redis-47ad2467de1d
public interface ChatroomRepository extends ReactiveCrudRepository<Chatroom, String>, CustomChatroomRepository {
}
