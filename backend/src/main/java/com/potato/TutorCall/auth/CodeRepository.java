package com.potato.TutorCall.auth;

import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CodeRepository {
  private final RedisTemplate<String, String> redisTemplate;
  private static final String hash_key = "email-";
  private static final Long expiredTime = 300L; // 5분

  // key는 EAMIL입니다.
  public void setCode(String key, String code) {
    ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
    valueOperations.set(hash_key + key, code, expiredTime, TimeUnit.SECONDS);
  }

  public String getCode(String key) {
    return (String) redisTemplate.opsForValue().get(hash_key + key);
  }

  public void deleteCode(String key) {
    redisTemplate.delete(hash_key + key);
  }
}
