package com.potato.TutorCall.review.controller;

import com.potato.TutorCall.auth.SessionKey;
import com.potato.TutorCall.auth.dto.UserSessionDto;
import com.potato.TutorCall.common.dto.CreatedResponseDto;
import com.potato.TutorCall.common.dto.MessageResponseDto;
import com.potato.TutorCall.review.dto.ReviewRequestDto;
import com.potato.TutorCall.review.service.ReviewService;
import com.potato.TutorCall.tutorcall.service.TutorCallService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/tutorcall/{tutorCallId}")
    public ResponseEntity<?> saveTutorCallReview(@PathVariable("tutorCallId") Long id,
                                                 @RequestBody ReviewRequestDto dto,
                                                 HttpSession session) {
        UserSessionDto userSessionDto = (UserSessionDto) session.getAttribute(SessionKey.USER);
        Long reviewId = reviewService.saveTutorCallReview(userSessionDto.getId(), id, dto);
        log.info(userSessionDto.getId() + " 회원이 튜터콜" + id + "의 리뷰를 등록했습니다.");
        return ResponseEntity.status(HttpStatus.CREATED).body(new CreatedResponseDto(reviewId, "리뷰가 등록되었습니다."));
    }

    @PostMapping("/lecture/{lectureId}")
    public ResponseEntity<?> saveLectureReview(@PathVariable("lectureId") Long id,
                                               @RequestBody ReviewRequestDto dto,
                                               HttpSession session) {
        UserSessionDto userSessionDto = (UserSessionDto) session.getAttribute(SessionKey.USER);
        Long reviewId = reviewService.saveLectureReview(userSessionDto.getId(), id, dto);
        log.info(userSessionDto.getId() + " 회원이 과외" + id + "의 리뷰를 등록했습니다.");
        return ResponseEntity.status(HttpStatus.CREATED).body(new CreatedResponseDto(id, "리뷰가 등록되었습니다."));
    }

    @PatchMapping("/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable("reviewId") Long id,
                                          @RequestBody ReviewRequestDto dto,
                                          HttpSession session) {
        UserSessionDto userSessionDto = (UserSessionDto) session.getAttribute(SessionKey.USER);
        reviewService.updateReview(userSessionDto.getId(), id, dto);
        log.info(userSessionDto.getId() + " 회원이 과외" + id + "의 리뷰를 등록했습니다.");
        return ResponseEntity.ok(new MessageResponseDto("리뷰가 수정되었습니다."));
    }


    @GetMapping("/tutor/{tutorId}")
    public ResponseEntity<?> getTutorReview (@PathVariable("tutorId") Long id, Pageable page){
        return new ResponseEntity<>(this.reviewService.tutorReviews(id, page), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserReview (@PathVariable("userId") Long id, Pageable page){
        return new ResponseEntity<>(this.reviewService.userReview(id, page), HttpStatus.OK);
    }
}
