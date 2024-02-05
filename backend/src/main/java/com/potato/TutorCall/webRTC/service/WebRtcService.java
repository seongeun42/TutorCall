package com.potato.TutorCall.webRTC.service;

import com.potato.TutorCall.exception.customException.DuplicatedException;
import com.potato.TutorCall.exception.customException.NotFoundException;
import com.potato.TutorCall.review.domain.StudyType;
import io.openvidu.java.client.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class WebRtcService {

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 화상강의룸 세션 만드는 함수
     * @param id 튜터콜 or 과외의 id
     * @param type 과외 타입
     * @param openVidu
     * @return 생성된 sessionId
     */
    public String createSession(Long id, StudyType type, OpenVidu openVidu) throws OpenViduJavaClientException, OpenViduHttpException {
        if (redisTemplate.opsForValue().get(type.getValue() + id) != null) {
            throw new DuplicatedException("세션이 이미 존재합니다.");
        }
        Map<String, Object> prams = new HashMap<>();
        prams.put("customSessionId", type.getValue() + id);
        SessionProperties properties = SessionProperties.fromJson(prams).build();
        Session lectureSession = openVidu.createSession(properties);
        redisTemplate.opsForValue().set(type.getValue() + id, lectureSession.getSessionId());
        return lectureSession.getSessionId();
    }

    public String getConnection(Long id, Long userId, StudyType type, OpenVidu openVidu) throws OpenViduJavaClientException, OpenViduHttpException {
        if (redisTemplate.opsForValue().get(type.getValue() + id) == null) {
            throw new NotFoundException("존재하지 않는 세션입니다.");
        }
        openVidu.fetch();
        Session session = openVidu.getActiveSession(type.getValue() + id);
        Connection connection = session.createConnection();
        redisTemplate.opsForHash().putIfAbsent(session.getSessionId() + "participant", "user" + userId, connection.getConnectionId());
        return connection.getToken();
    }

    public boolean removeConnection(Long id, Long userId, StudyType type, OpenVidu openVidu) throws OpenViduJavaClientException, OpenViduHttpException {
        if (redisTemplate.opsForValue().get(type.getValue() + id) == null) {
            throw new NotFoundException("존재하지 않는 세션입니다.");
        }
        openVidu.fetch();
        Session session = openVidu.getActiveSession(type.getValue() + id);
        redisTemplate.opsForHash().delete(session.getSessionId() + "participant", "user" + userId);
        // 참가자가 0이면 삭제
        if (redisTemplate.opsForHash().size(session.getSessionId() + "participant") == 0) {
            redisTemplate.delete(session.getSessionId() + "participant");
            redisTemplate.delete(type.getValue() + id);
            session.close();
            return true;
        }
        return false;
    }

}