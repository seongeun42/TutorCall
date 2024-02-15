package com.potato.TutorCall.config;

import com.potato.TutorCall.chat.domain.ChatMessage;
import com.potato.TutorCall.chat.domain.ChatParticipants;
import com.potato.TutorCall.chat.domain.Chatroom;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.io.Serializable;

@Configuration
@NoArgsConstructor
public class RedisConfig {
  @Value("${spring.data.redis.host}")
  private String host;

  @Value("${spring.data.redis.port}")
  private int port;

  @Value("${spring.data.redis.password}")
  private String password;

  @Bean
  public RedisConnectionFactory redisConnectionFactory() {

    RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
    redisStandaloneConfiguration.setHostName(host);
    redisStandaloneConfiguration.setPort(port);
    redisStandaloneConfiguration.setPassword(password);
    return new LettuceConnectionFactory(redisStandaloneConfiguration);
  }

  //  @Bean
  //  public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
  //    return new GenericJackson2JsonRedisSerializer();
  //  }

  @Bean
  public RedisTemplate<?, ?> redisTemplate() {
    RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisConnectionFactory());
    redisTemplate.setDefaultSerializer(new StringRedisSerializer());
    return redisTemplate;
  }

  @Bean
  public ReactiveRedisTemplate<?, ?> reactiveRedisTemplate(
      ReactiveRedisConnectionFactory redisConnectionFactory) {
    return new ReactiveRedisTemplate<>(redisConnectionFactory, RedisSerializationContext.string());
  }

  @Bean
  public ReactiveRedisOperations<String, ChatMessage> ChatMessageRedisOperations(LettuceConnectionFactory connectionFactory) {
    RedisSerializationContext<String, ChatMessage> serializationContext = RedisSerializationContext
            .<String, ChatMessage>newSerializationContext(new StringRedisSerializer())
            .key(new StringRedisSerializer())
            .value(new GenericToStringSerializer<>(ChatMessage.class))
            .hashKey(new StringRedisSerializer())
            .hashValue(new GenericJackson2JsonRedisSerializer())
            .build();
    return new ReactiveRedisTemplate<>(connectionFactory, serializationContext);
  }

  @Bean
  public ReactiveRedisOperations<String, Chatroom> ChatroomRedisOperations(LettuceConnectionFactory connectionFactory) {
    RedisSerializationContext<String, Chatroom> serializationContext = RedisSerializationContext
            .<String, Chatroom>newSerializationContext(new StringRedisSerializer())
            .key(new StringRedisSerializer())
            .value(new GenericToStringSerializer<>(Chatroom.class))
            .hashKey(new StringRedisSerializer())
            .hashValue(new GenericJackson2JsonRedisSerializer())
            .build();
    return new ReactiveRedisTemplate<>(connectionFactory, serializationContext);
  }

  @Bean
  public ReactiveRedisOperations<String, ChatParticipants> ChatParticipantsRedisOperations(LettuceConnectionFactory connectionFactory) {
    RedisSerializationContext<String, ChatParticipants> serializationContext = RedisSerializationContext
            .<String, ChatParticipants>newSerializationContext(new StringRedisSerializer())
            .key(new StringRedisSerializer())
            .value(new GenericToStringSerializer<>(ChatParticipants.class))
            .hashKey(new StringRedisSerializer())
            .hashValue(new GenericJackson2JsonRedisSerializer())
            .build();
    return new ReactiveRedisTemplate<>(connectionFactory, serializationContext);
  }
}
