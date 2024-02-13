package com.potato.TutorCall.tutorcall.controller;

import com.potato.TutorCall.exception.customException.OutOfRangeException;
import com.potato.TutorCall.review.domain.StudyType;
import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.tutor.domain.Tutor;
import com.potato.TutorCall.tutor.dto.TagDto;
import com.potato.TutorCall.tutor.dto.TutorDetailDto;
import com.potato.TutorCall.tutor.service.TagService;
import com.potato.TutorCall.tutor.service.TutorService;
import com.potato.TutorCall.tutorcall.domain.RequestCall;
import com.potato.TutorCall.tutorcall.domain.ResponseCall;
import com.potato.TutorCall.tutorcall.domain.TutorCall;
import com.potato.TutorCall.tutorcall.dto.request.StudentAcceptRequestDto;
import com.potato.TutorCall.tutorcall.dto.request.StudentRequestDto;
import com.potato.TutorCall.tutorcall.dto.request.TutorAcceptRequestDto;
import com.potato.TutorCall.tutorcall.dto.response.MatchResponseMessage;
import com.potato.TutorCall.tutorcall.dto.response.TutorAcceptMessage;
import com.potato.TutorCall.tutorcall.dto.response.TutorCallMessage;
import com.potato.TutorCall.tutorcall.service.TutorCallService;
import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.dto.UserSimpleDto;
import com.potato.TutorCall.user.service.UserService;
import com.potato.TutorCall.webRTC.service.WebRtcService;
import io.openvidu.java.client.OpenVidu;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SocketController {

    private final OpenVidu openVidu;
    private final TagService tagService;
    private final UserService userService;
    private final TutorService tutorService;
    private final WebRtcService webRtcService;
    private final TutorCallService tutorCallService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    /**
     * 학생이 튜터콜 요청
     */
    @MessageMapping("/tag/{tagId}")
    @SendTo("/sub/tag/{tagId}")
    public TutorCallMessage requestTutorCall(@DestinationVariable(value = "tagId") Long tagId, @RequestBody StudentRequestDto dto, Principal principal) {
        User user = userService.findById(dto.getUserId());
        Tag tag = tagService.findById(dto.getTagId());
        RequestCall requestCall = RequestCall.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .caller(dto.getUserId())
                .tag(tag)
                .build();
        tutorCallService.tutorCallRequestSave(requestCall);
        log.info(dto.getUserId() + "님이 튜터콜을 요청했습니다.");
        return TutorCallMessage.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .tag(new TagDto(tag))
                .user(new UserSimpleDto(user))
                .build();
    }

    /**
     * 선생님이 학생의 요청을 수락
     */
    @MessageMapping("/tutorcall/{reqUuid}")
    @SendTo("/sub/tutorcall/{reqUuid}")
    public TutorAcceptMessage tutorAccept(@DestinationVariable(value = "reqUuid") UUID reqUuid, @RequestBody TutorAcceptRequestDto dto, Principal principal) {
        // 선생님 수락 구현
        RequestCall requestCall = tutorCallService.getRequestCall(reqUuid);
        if (requestCall.getTutorCount() >= 3) {
            throw new OutOfRangeException("최대 3명의 선생님만 수락할 수 있습니다.");
        }
        TutorDetailDto tutorDetailDto = tutorService.getTutorDetail(dto.getTutorId());
        ResponseCall responseCall = ResponseCall.builder()
                .id(dto.getId())
                .tutor(dto.getTutorId())
                .call(reqUuid)
                .build();
        tutorCallService.tutorCallResponseSave(reqUuid, responseCall);
        return TutorAcceptMessage.builder()
                .reqId(reqUuid)
                .resId(dto.getId())
                .tutor(tutorDetailDto)
                .build();
    }

    /**
     * 학생이 선생님을 골라 매칭 수락
     */
    @MessageMapping("/tutorcall/answer/{resUuid}")
    @SendTo("/sub/tutorcall/answer/{resUuid}")
    public MatchResponseMessage matchOk(@DestinationVariable(value = "resUuid") UUID resUuid, @RequestBody StudentAcceptRequestDto dto, Principal principal) throws OpenViduJavaClientException, OpenViduHttpException {
        // 학생 수락 및 매칭 + 세션 생성 구현
        RequestCall requestCall = tutorCallService.getRequestCall(dto.getReqId());
        User user = userService.findById(requestCall.getCaller());
        Tutor tutor = tutorService.findById(dto.getTutor());
        TutorCall tutorCall = TutorCall.builder()
                .tutor(tutor)
                .user(user)
                .problemContent(requestCall.getContent())
                .tag(requestCall.getTag())
                .liveState(true)
                .build();
        Long tutorCallId = tutorCallService.save(tutorCall);
        String roomId = webRtcService.createSession(tutorCallId, StudyType.TUTORCALL, openVidu);
        tutorCallService.startTutorCallLive(tutorCallId, roomId);
        log.info(tutorCallId + "튜터콜의 화상강의룸 세션이 생성되었습니다.");
        return new MatchResponseMessage(true, roomId);
    }

    /**
     * 학생이 선생님을 골라 매칭 거절
     */
    @MessageMapping("/tutorcall/answer/{resUuid}/rejection")
    @SendTo("/sub/tutorcall/answer/{resUuid}")
    public MatchResponseMessage matchRejection(@DestinationVariable(value = "resUuid") UUID resUuid, Principal principal) throws OpenViduJavaClientException, OpenViduHttpException {
        return new MatchResponseMessage(false, null);
    }

}
