package com.potato.TutorCall.lecture.controller;

import com.potato.TutorCall.auth.SessionKey;
import com.potato.TutorCall.auth.dto.UserSessionDto;
import com.potato.TutorCall.common.dto.CreatedResponseDto;
import com.potato.TutorCall.common.dto.MessageResponseDto;
import com.potato.TutorCall.exception.customException.ForbiddenException;
import com.potato.TutorCall.lecture.domain.Lecture;
import com.potato.TutorCall.lecture.domain.LectureParticipant;
import com.potato.TutorCall.lecture.dto.*;
import com.potato.TutorCall.lecture.service.LectureParticipantService;
import com.potato.TutorCall.lecture.service.LectureService;
import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.tutor.domain.Tutor;
import com.potato.TutorCall.tutor.service.TagService;
import com.potato.TutorCall.tutor.service.TutorService;
import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/lecture")
public class LectureController {

    private final TagService tagService;
    private final UserService userService;
    private final TutorService tutorService;
    private final LectureService lectureService;
    private final LectureParticipantService participantService;

    @PostMapping("/promotion")
    public ResponseEntity<?> saveLecture(@RequestBody LectureRequestDto dto, HttpSession session) {
        UserSessionDto user = (UserSessionDto) session.getAttribute(SessionKey.USER);
        Tutor tutor = tutorService.findById(user.getId());
        Tag tag = tagService.findById(dto.getTagId());
        Lecture lecture = Lecture.builder()
                .tutor(tutor)
                .promotionTitle(dto.getPromotionTitle())
                .promotionContent(dto.getPromotionContent())
                .promotionDue(dto.getPromotionDue())
                .maxParticipants(dto.getMaxParticipant())
                .price(dto.getPrice())
                .tag(tag)
                .build();
        Long lectureId = lectureService.save(lecture);
        log.info(user.getId() + "회원이 과외" + lectureId + "를 등록했습니다.");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreatedResponseDto(lectureId, "과외 모집글을 등록했습니다."));
    }

    @PatchMapping("/promotion/{lectureId}")
    public ResponseEntity<?> updateLecture(@PathVariable("lectureId") Long lectureId,
                                           @RequestBody LectureRequestDto dto,
                                           HttpSession session) {
        UserSessionDto user = (UserSessionDto) session.getAttribute(SessionKey.USER);
        lectureService.updateLecture(user.getId(), lectureId, dto);
        log.info(user.getId() + "회원이 과외" + lectureId + "를 수정했습니다.");
        return ResponseEntity.ok().body(new CreatedResponseDto(lectureId, "과외 모집글을 수정했습니다."));
    }

    @DeleteMapping("/promotion/{lectureId}")
    public ResponseEntity<?> deleteLecture(@PathVariable("lectureId") Long lectureId,
                                           HttpSession session) {
        UserSessionDto user = (UserSessionDto) session.getAttribute(SessionKey.USER);
        lectureService.deleteLecture(user.getId(), lectureId);
        log.info(user.getId() + "회원이 과외" + lectureId + "를 삭제했습니다.");
        return ResponseEntity.ok().body(new CreatedResponseDto(lectureId, "과외 모집글을 삭제했습니다."));
    }

    @GetMapping()
    public ResponseEntity<?> getLectureList(@RequestParam(value = "keyword", required = false) String keyword,
                                            @RequestParam(value = "tag", required = false) Long tag,
                                            @RequestParam(value = "state", required = false) Boolean state,
                                            Pageable pageable) {
        LectureSearchCondition condition = LectureSearchCondition.builder()
                .keyword(keyword)
                .tag(tag)
                .state(state)
                .build();
        Page<LectureListResponseDto> result = lectureService.getLectureList(condition, pageable);
        log.info("과외 모집 게시판을 조회했습니다.");
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/promotion/{lectureId}")
    public ResponseEntity<?> getLecture(@PathVariable("lectureId") Long lectureId) {
        Lecture lecture = lectureService.findById(lectureId);
        LecturePromotionResponseDto dto = new LecturePromotionResponseDto(lecture);
        log.info("과외 모집글을 조회했습니다.");
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/participant/{lectureId}")
    public ResponseEntity<?> registLecture(@PathVariable("lectureId") Long lectureId, HttpSession session) {
        UserSessionDto userSessionDto = (UserSessionDto) session.getAttribute(SessionKey.USER);
        Lecture lecture = lectureService.findById(lectureId);
        User user = userService.findById(userSessionDto.getId());
        Long id = participantService.registLecture(lecture, user);
        log.info(userSessionDto.getId() + "님이 과외" + lectureId + "를 신청했습니다.");
        return ResponseEntity.status(HttpStatus.CREATED).body(new CreatedResponseDto(id, "과외에 등록되었습니다."));
    }

    @DeleteMapping("/participant/{lectureId}")
    public ResponseEntity<?> unRegistLecture(@PathVariable("lectureId") Long lectureId, HttpSession session) {
        UserSessionDto userSessionDto = (UserSessionDto) session.getAttribute(SessionKey.USER);
        Lecture lecture = lectureService.findById(lectureId);
        participantService.unRegistLecture(lecture, userSessionDto.getId());
        log.info("회원" + userSessionDto.getId() + "님이 과외" + lectureId + "를 신청 취소했습니다.");
        return ResponseEntity.ok().body(new MessageResponseDto("과외 등록이 취소되었습니다."));
    }

}
