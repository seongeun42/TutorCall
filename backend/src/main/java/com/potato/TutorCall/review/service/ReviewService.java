package com.potato.TutorCall.review.service;

import com.potato.TutorCall.exception.customException.ForbiddenException;
import com.potato.TutorCall.exception.customException.NotFoundException;
import com.potato.TutorCall.lecture.domain.Lecture;
import com.potato.TutorCall.lecture.service.LectureParticipantService;
import com.potato.TutorCall.lecture.service.LectureService;
import com.potato.TutorCall.review.domain.Review;
import com.potato.TutorCall.review.domain.StudyType;
import com.potato.TutorCall.review.dto.ReviewRequestDto;
import com.potato.TutorCall.review.dto.TutorReviewResponseDto;
import com.potato.TutorCall.review.repository.ReviewRepository;
import com.potato.TutorCall.tutor.domain.Tutor;
import com.potato.TutorCall.tutorcall.domain.TutorCall;
import com.potato.TutorCall.tutorcall.service.TutorCallService;
import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final LectureService lectureService;
    private final UserService userService;
    private final LectureParticipantService lectureParticipantService;
    private final TutorCallService tutorCallService;
    /**
     * 튜터콜 리뷰를 저장하는 함수
     * @param userId
     * @param tutorCallId
     * @param dto review 내용
     * @return 저장된 Review의 id
     */
    public Long saveTutorCallReview(Long userId, Long tutorCallId, ReviewRequestDto dto) {
        TutorCall tutorCall = tutorCallService.findById(tutorCallId);
        if (!userId.equals(tutorCall.getUser().getId())) {
            throw new ForbiddenException("해당 튜터콜을 수강한 학생이 아닙니다.");
        }
        // 리뷰 저장
        Long reviewId = save(tutorCall.getTutor(), tutorCall.getUser(), dto, StudyType.TUTORCALL);
        // 선생님의 평가 점수에 반영
        tutorRateUpdate(tutorCall.getTutor());
        return reviewId;
    }

    /**
     * 과외 리뷰를 저장하는 함수
     * @param userId
     * @param lectureId
     * @param dto review 내용
     * @return 저장된 Review의 id
     */
    public Long saveLectureReview(Long userId, Long lectureId, ReviewRequestDto dto) {
        Lecture lecture = lectureService.findById(lectureId);
        User user = userService.findById(userId);
        if (!lectureParticipantService.existParticipant(lecture.getId(), user.getId())) {
            throw new ForbiddenException("해당 과외를 수강한 학생이 아닙니다.");
        }
        // 리뷰 저장
        Long reviewId = save(lecture.getTutor(), user, dto, StudyType.LECTURE);
        // 선생님의 평가 점수에 반영
        tutorRateUpdate(lecture.getTutor());
        return reviewId;
    }

    /**
     * Review 수정 함수
     * @param userId
     * @param reviewId
     * @param dto 수정된 Review 내용
     */
    public void updateReview(Long userId, Long reviewId, ReviewRequestDto dto) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 리뷰입니다."));
        if (!review.getReviewer().getId().equals(userId)) {
            throw new ForbiddenException("리뷰의 작성자가 아닙니다.");
        }
        if (dto.getMannerRate() != null) {
            review.changeMannerRate(dto.getMannerRate());
        }
        if (dto.getCommunicationRate() != null) {
            review.changeCommunicationRate(dto.getCommunicationRate());
        }
        if (dto.getProfessionalismRate() != null) {
            review.changeProfessionalismRate(dto.getProfessionalismRate());
        }
        if (dto.getContent() != null) {
            review.changeContent(dto.getContent());
        }
        // 선생님의 평가 점수에 반영
        tutorRateUpdate(review.getTutor());
    }

    // private 함수
    /**
     * 리뷰 저장하는 함수
     * @param tutor
     * @param user
     * @param dto Review 내용
     * @return 저장된 Review id
     */
    private Long save(Tutor tutor, User user, ReviewRequestDto dto, StudyType type) {
        Review review = Review.builder()
                .studyType(type)
                .tutor(tutor)
                .reviewer(user)
                .mannerRate(dto.getMannerRate())
                .communicationRate(dto.getCommunicationRate())
                .professionalismRate(dto.getProfessionalismRate())
                .content(dto.getContent())
                .build();
        return reviewRepository.save(review).getId();
    }

    /**
     * Tutor의 평가 점수에 리뷰 점수 반영하는 함수
     * @param tutor
     */
    private void tutorRateUpdate(Tutor tutor) {
        tutor.changeMannerRate(reviewRepository.getTutorMannerAvg(tutor));
        tutor.changeCommunicationRate(reviewRepository.getTutorCommnunicationAvg(tutor));
        tutor.changeProfessionalismRate(reviewRepository.getTutorProfessionalismAvg(tutor));
    }

    public Page<TutorReviewResponseDto> tutorReviews(Long id, Pageable pageable) {
        LocalDateTime start = LocalDateTime.of(LocalDate.now().minusDays(30), LocalTime.of(0,0,0) );
        LocalDateTime end = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));
        return reviewRepository.findReviewsByTutor_IdAndCreatedAtBetweenOrderByCreatedAtDesc(id,start, end, pageable).map(TutorReviewResponseDto::new);
    }

    @Transactional(readOnly = true)
    public List<Review> getLectureReviews(Lecture lecture) {
        return reviewRepository.findAllByLecture(lecture);
    }

}