package com.potato.TutorCall.chat.repository;

import com.potato.TutorCall.chat.domain.ChatParticipants;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ChatparticipantsRepoImpl implements ChatparticipantsRepository {
  @Override
  public <S extends ChatParticipants> Mono<S> save(S entity) {
    return null;
  }

  @Override
  public <S extends ChatParticipants> Flux<S> saveAll(Iterable<S> entities) {
    return null;
  }

  @Override
  public <S extends ChatParticipants> Flux<S> saveAll(Publisher<S> entityStream) {
    return null;
  }

  @Override
  public Mono<ChatParticipants> findById(String s) {
    return null;
  }

  @Override
  public Mono<ChatParticipants> findById(Publisher<String> id) {
    return null;
  }

  @Override
  public Mono<Boolean> existsById(String s) {
    return null;
  }

  @Override
  public Mono<Boolean> existsById(Publisher<String> id) {
    return null;
  }

  @Override
  public Flux<ChatParticipants> findAll() {
    return null;
  }

  @Override
  public Flux<ChatParticipants> findAllById(Iterable<String> strings) {
    return null;
  }

  @Override
  public Flux<ChatParticipants> findAllById(Publisher<String> idStream) {
    return null;
  }

  @Override
  public Mono<Long> count() {
    return null;
  }

  @Override
  public Mono<Void> deleteById(String s) {
    return null;
  }

  @Override
  public Mono<Void> deleteById(Publisher<String> id) {
    return null;
  }

  @Override
  public Mono<Void> delete(ChatParticipants entity) {
    return null;
  }

  @Override
  public Mono<Void> deleteAllById(Iterable<? extends String> strings) {
    return null;
  }

  @Override
  public Mono<Void> deleteAll(Iterable<? extends ChatParticipants> entities) {
    return null;
  }

  @Override
  public Mono<Void> deleteAll(Publisher<? extends ChatParticipants> entityStream) {
    return null;
  }

  @Override
  public Mono<Void> deleteAll() {
    return null;
  }
}
