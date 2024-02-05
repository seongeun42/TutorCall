package com.potato.TutorCall.review.service;

import static org.assertj.core.api.Assertions.*;

import com.potato.TutorCall.lecture.domain.Lecture;
import com.potato.TutorCall.lecture.repository.LectureRepository;
import com.potato.TutorCall.review.domain.Review;
import com.potato.TutorCall.review.dto.TutorReviewResponseDto;
import com.potato.TutorCall.review.repository.ReviewRepository;
import com.potato.TutorCall.tutor.domain.Tutor;
import com.potato.TutorCall.tutor.repository.TutorRepository;
import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.domain.enums.RoleType;
import com.potato.TutorCall.user.domain.enums.SnsType;
import com.potato.TutorCall.user.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TutorReviewTest {

  @Autowired ReviewService reviewService;

  @Autowired UserRepository userRepository;
  @Autowired ReviewRepository reviewRepository;
  @Autowired TutorRepository tutorRepository;
  @Autowired LectureRepository lectureRepository;

  @Test
  @DisplayName("해당 유저의 리뷰 30개를 가져온다.")
  @Transactional
  public void tutorReviewTest() {
    User user1 =
        User.builder()
            .role(RoleType.USER)
            .email("test@naver.com")
            .nickname("test")
            .point(0)
            .profile("test")
            .sns(SnsType.NAVER)
            .build();

    User user2 =
        User.builder()
            .role(RoleType.TUTOR)
            .email("test@naver.com")
            .nickname("test")
            .point(0)
            .profile("test")
            .sns(SnsType.NAVER)
            .build();

    this.userRepository.save(user1);
    this.userRepository.save(user2);
    Tutor tutor = Tutor.builder().introduction("test").user(user2).build();
    tutorRepository.save(tutor);
    Lecture lecture =
        Lecture.builder()
            .tutor(tutor)
            .price(1000)
            .maxParticipants(300)
            .promotionContent("test")
            .promotionDue(LocalDateTime.now())
            .promotionTitle("dsf")
            .build();
    lectureRepository.save(lecture);
    for (int i = 0; i < 100; i++) {
      Review review =
          Review.builder()
              .content("TEST")
              .professionalismRate(5)
              .communicationRate(1)
              .lecture(lecture)
              .tutor(tutor)
              .mannerRate(1)
              .reviewer(user1)
              .build();
      reviewRepository.save(review);
    }
    Pageable pageable = Pageable.ofSize(5).withPage(1);
    Page<TutorReviewResponseDto> getReview =
        this.reviewService.tutorReviews(tutor.getId(), pageable);
    List<TutorReviewResponseDto> list = getReview.getContent();
    assertThat(getReview.getContent().size()).isEqualTo(5);

    pageable = Pageable.ofSize(1).withPage(1);
    getReview = this.reviewService.tutorReviews(tutor.getId(), pageable);
    assertThat(getReview.getContent().size()).isEqualTo(1);

    pageable = Pageable.ofSize(101).withPage(1);
    getReview = this.reviewService.tutorReviews(tutor.getId(), pageable);
    assertThat(getReview.getContent().size()).isEqualTo(0);

    pageable = Pageable.ofSize(101).withPage(0);
    getReview = this.reviewService.tutorReviews(tutor.getId(), pageable);
    assertThat(getReview.getContent().size()).isEqualTo(100);

    pageable = Pageable.ofSize(15).withPage(6);
    getReview = this.reviewService.tutorReviews(tutor.getId(), pageable);
    assertThat(getReview.getContent().size()).isEqualTo(10);
  }
}
