package com.potato.TutorCall.webRTC.controller;

import com.potato.TutorCall.auth.SessionKey;
import com.potato.TutorCall.auth.dto.UserSessionDto;
import com.potato.TutorCall.common.dto.MessageResponseDto;
import com.potato.TutorCall.exception.customException.ForbiddenException;
import com.potato.TutorCall.lecture.domain.Lecture;
import com.potato.TutorCall.lecture.service.LectureParticipantService;
import com.potato.TutorCall.lecture.service.LectureService;
import com.potato.TutorCall.review.domain.StudyType;
import com.potato.TutorCall.tutorcall.domain.TutorCall;
import com.potato.TutorCall.tutorcall.service.TutorCallService;
import com.potato.TutorCall.webRTC.dto.ConnectionResponseDto;
import com.potato.TutorCall.webRTC.dto.CreateSessionResponseDto;
import com.potato.TutorCall.webRTC.service.WebRtcService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.openvidu.java.client.OpenVidu;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WebRtcController {

    @Value("${OPENVIDU_URL}")
    private String OPENVIDU_URL;

    @Value("${OPENVIDU_SECRET}")
    private String OPENVIDU_SECRET;

    private OpenVidu openvidu;
    private final WebRtcService webRtcService;
    private final LectureService lectureService;
    private final TutorCallService tutorCallService;
    private final LectureParticipantService participantService;

    @PostConstruct
    public void init() {
        this.openvidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
    }

    @PostMapping("/lecture/{lectureId}/session")
    public ResponseEntity<?> createLectureRoom(@PathVariable("lectureId") Long lectureId, HttpSession session)
            throws OpenViduJavaClientException, OpenViduHttpException {
        UserSessionDto userSessionDto = (UserSessionDto) session.getAttribute(SessionKey.USER);
        Lecture lecture = lectureService.findById(lectureId);
        if (!userSessionDto.getId().equals(lecture.getTutor().getId())) {
            throw new ForbiddenException("과외룸을 생성할 권한이 없습니다.");
        }
        String roomId = webRtcService.createSession(lectureId, StudyType.LECTURE, openvidu);
        lectureService.startLectureLive(lectureId, roomId);
        log.info(lectureId + "과외의 화상강의룸 세션이 생성되었습니다.");
        return ResponseEntity.status(HttpStatus.CREATED).body(new CreateSessionResponseDto(roomId, "과외룸이 생성되었습니다."));
    }

    @PostMapping("/tutorcall/{tutorcallId}/session")
    public ResponseEntity<?> createTutorCallRoom(@PathVariable("tutorcallId") Long tutorCallId)
            throws OpenViduJavaClientException, OpenViduHttpException {
        String roomId = webRtcService.createSession(tutorCallId, StudyType.TUTORCALL, openvidu);
        tutorCallService.startTutorCallLive(tutorCallId, roomId);
        log.info(tutorCallId + "튜터콜의 화상강의룸 세션이 생성되었습니다.");
        return ResponseEntity.status(HttpStatus.CREATED).body(new CreateSessionResponseDto(roomId, "튜터콜룸이 생성되었습니다."));
    }

    @PostMapping("/lecture/{lectureId}/connection")
    public ResponseEntity<?> createLectureConnection(@PathVariable("lectureId") Long lectureId, HttpSession session)
            throws OpenViduJavaClientException, OpenViduHttpException {
        UserSessionDto userSessionDto = (UserSessionDto) session.getAttribute(SessionKey.USER);
        Lecture lecture = lectureService.findById(lectureId);
        if (!userSessionDto.getId().equals(lecture.getTutor().getId())
                && !participantService.existParticipant(lectureId, userSessionDto.getId())) {
            throw new ForbiddenException("입장 권한이 없습니다.");
        }
        String token = webRtcService.getConnection(lectureId, userSessionDto.getId(), StudyType.LECTURE, openvidu);
        log.info(userSessionDto.getId() + "님의 " + lectureId + "과외 화상강의룸 세션 연결에 성공했습니다.");
        return ResponseEntity.status(HttpStatus.CREATED).body(new ConnectionResponseDto(token, "연결에 성공했습니다."));
    }

    @PostMapping("/tutorcall/{tutorcallId}/connection")
    public ResponseEntity<?> createTutorCallConnection(@PathVariable("tutorcallId") Long tutorCallId, HttpSession session)
            throws OpenViduJavaClientException, OpenViduHttpException {
        UserSessionDto userSessionDto = (UserSessionDto) session.getAttribute(SessionKey.USER);
        TutorCall tutorCall = tutorCallService.findById(tutorCallId);
        if (!userSessionDto.getId().equals(tutorCall.getTutor().getId()) && !userSessionDto.getId().equals(tutorCall.getUser().getId())) {
            throw new ForbiddenException("입장 권한이 없습니다.");
        }
        String token = webRtcService.getConnection(tutorCallId, userSessionDto.getId(), StudyType.TUTORCALL, openvidu);
        log.info(userSessionDto.getId() + "님의 " + tutorCallId + "튜터콜 화상강의룸 세션 연결에 성공했습니다.");
        return ResponseEntity.status(HttpStatus.CREATED).body(new ConnectionResponseDto(token, "연결에 성공했습니다."));
    }

    @DeleteMapping("/lecture/{lectureId}/disconnection")
    public ResponseEntity<?> removeLectureConnection(@PathVariable("lectureId") Long lectureId, HttpSession session)
            throws OpenViduJavaClientException, OpenViduHttpException {
        UserSessionDto userSessionDto = (UserSessionDto) session.getAttribute(SessionKey.USER);
        if (webRtcService.removeConnection(lectureId, userSessionDto.getId(), StudyType.LECTURE, openvidu)) {
            lectureService.endLectureLive(lectureId);
        }
        log.info(userSessionDto.getId() + "님이 " + lectureId + "과외 화상강의룸을 나갔습니다.");
        return ResponseEntity.ok().body(new MessageResponseDto("연결을 해제했습니다."));
    }

    @DeleteMapping("/tutorcall/{tutorcallId}/disconnection")
    public ResponseEntity<?> removeTutorCallConnection(@PathVariable("tutorcallId") Long tutorCallId, HttpSession session)
            throws OpenViduJavaClientException, OpenViduHttpException {
        UserSessionDto userSessionDto = (UserSessionDto) session.getAttribute(SessionKey.USER);
        if (webRtcService.removeConnection(tutorCallId, userSessionDto.getId(), StudyType.TUTORCALL, openvidu)) {
            tutorCallService.endTutorCallLive(tutorCallId);
        }
        log.info(userSessionDto.getId() + "님이 " + tutorCallId + "튜터콜 화상강의룸을 나갔습니다.");
        return ResponseEntity.ok().body(new MessageResponseDto("연결을 해제했습니다."));
    }

}
