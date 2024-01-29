package com.potato.TutorCall.lecture.controller;

import com.potato.TutorCall.auth.SessionKey;
import com.potato.TutorCall.auth.dto.UserSessionDto;
import com.potato.TutorCall.common.dto.CreatedResponseDto;
import com.potato.TutorCall.lecture.domain.Lecture;
import com.potato.TutorCall.lecture.dto.LectureRequestDto;
import com.potato.TutorCall.lecture.service.LectureService;
import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.tutor.domain.Tutor;
import com.potato.TutorCall.tutor.service.TagService;
import com.potato.TutorCall.tutor.service.TutorService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/lecture")
public class LectureController {

    private final LectureService lectureService;
    private final TutorService tutorService;
    private final TagService tagService;

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

}
