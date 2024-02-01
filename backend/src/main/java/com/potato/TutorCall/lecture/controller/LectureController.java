package com.potato.TutorCall.lecture.controller;

import com.potato.TutorCall.auth.SessionKey;
import com.potato.TutorCall.auth.dto.UserSessionDto;
import com.potato.TutorCall.common.dto.CreatedResponseDto;
import com.potato.TutorCall.common.dto.MessageResponseDto;
import com.potato.TutorCall.exception.customException.ForbiddenException;
import com.potato.TutorCall.exception.customException.InvalidException;
import com.potato.TutorCall.lecture.domain.Lecture;
import com.potato.TutorCall.lecture.dto.*;
import com.potato.TutorCall.lecture.service.LectureParticipantService;
import com.potato.TutorCall.lecture.service.LectureService;
import com.potato.TutorCall.review.domain.Review;
import com.potato.TutorCall.review.service.ReviewService;
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

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/lecture")
public class LectureController {

    private final TagService tagService;
    private final UserService userService;
    private final TutorService tutorService;
    private final ReviewService reviewService;
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

    @PatchMapping("/state/{lectureId}")
    public ResponseEntity<?> changePromotionState(@PathVariable("lectureId") Long lectureId,
                                                 @RequestBody PromotionStateRequestDto dto,
                                                 HttpSession session) {
        UserSessionDto userSessionDto = (UserSessionDto) session.getAttribute(SessionKey.USER);
        if (dto.getState() == null)
            throw new InvalidException("상태 정보가 존재하지 않습니다.");
        Tutor tutor = tutorService.findById(userSessionDto.getId());
        lectureService.changePromotionState(lectureId, tutor, dto.getState());
        log.info("선생님" + userSessionDto.getId() + "님이 과외" + lectureId + " 상태를 변경했습니다.");
        return ResponseEntity.ok().body(new MessageResponseDto("과외 모집 상태가 변경되었습니다."));
    }
    
    @PatchMapping("/schedule/{lectureId}")
    public ResponseEntity<?> changeLectureTerm(@PathVariable("lectureId") Long lectureId,
                                                 @RequestBody LectureTermRequestDto dto,
                                                 HttpSession session) {
        UserSessionDto userSessionDto = (UserSessionDto) session.getAttribute(SessionKey.USER);
        if (dto.getStart() == null || dto.getEnd() == null)
            throw new InvalidException("날짜 정보가 존재하지 않습니다.");
        Tutor tutor = tutorService.findById(userSessionDto.getId());
        lectureService.changeLectureTerm(lectureId, tutor, dto.getStart(), dto.getEnd());
        log.info("선생님" + userSessionDto.getId() + "님이 과외" + lectureId + " 의 과외 기간을 변경했습니다.");
        return ResponseEntity.ok().body(new MessageResponseDto("과외 기간이 변경되었습니다."));
    }

    @GetMapping("/{lectureId}")
    private ResponseEntity<?> getLecture(@PathVariable("lectureId") Long lectureId,
                                         HttpSession session) {
        UserSessionDto userSessionDto = (UserSessionDto) session.getAttribute(SessionKey.USER);
        Lecture lecture = lectureService.findById(lectureId);
        Tutor tutor = lecture.getTutor();
        List<Review> reviews;
        if (userSessionDto.getId().equals(tutor.getId())) {
            reviews = reviewService.getLectureReviews(lecture, null);
        } else if (participantService.existParticipant(lectureId, userSessionDto.getId())) {
            reviews = reviewService.getLectureReviews(lecture, userSessionDto.getId());
        } else {
            throw new ForbiddenException("과외 조회 권한이 없습니다.");
        }
        LectureResponseDto result = new LectureResponseDto(lecture, tutor.getUser(), reviews);
        log.info("유저" + userSessionDto.getId() + "님이 과외" + lectureId + "를 조회했습니다.");
        return ResponseEntity.ok().body(result);
    }

}
