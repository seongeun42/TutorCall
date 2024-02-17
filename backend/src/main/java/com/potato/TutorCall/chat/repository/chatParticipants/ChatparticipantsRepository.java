package com.potato.TutorCall.chat.repository.chatParticipants;

import com.potato.TutorCall.chat.domain.ChatParticipants;
import org.springframework.data.repository.CrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ChatparticipantsRepository extends CrudRepository<ChatParticipants, String> {
  void deleteByUserIdAndChatroomId(Long userId, String chatroomId);
//  List<String> getParticipatingRooms(Long userId);
//  List<Long> getUsersInChatroom(String chatroomId);
  List<ChatParticipants> findAllByUserId(Long userId);
  List<ChatParticipants> findAllByChatroomId(String roomId);
  Long countUsersInRoom(String roomId);
}
