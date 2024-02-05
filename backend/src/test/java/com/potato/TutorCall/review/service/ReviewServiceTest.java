// package com.potato.TutorCall.review.service;
//
// import com.potato.TutorCall.lecture.service.LectureParticipantService;
// import com.potato.TutorCall.lecture.service.LectureService;
// import com.potato.TutorCall.review.domain.Review;
// import com.potato.TutorCall.review.dto.TutorReviewResponseDto;
// import com.potato.TutorCall.review.repository.ReviewRepository;
// import com.potato.TutorCall.tutor.domain.Tutor;
// import com.potato.TutorCall.tutor.repository.TutorTagRepository;
// import com.potato.TutorCall.tutor.service.TutorService;
// import com.potato.TutorCall.tutorcall.service.TutorCallService;
// import com.potato.TutorCall.user.domain.User;
// import com.potato.TutorCall.user.domain.enums.RoleType;
// import com.potato.TutorCall.user.service.UserService;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageImpl;
// import org.springframework.data.domain.Pageable;
//
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
//
// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;
// @SpringBootTest
// @ExtendWith(MockitoExtension.class)
// class ReviewServiceTest {
//    private ReviewService reviewService;
//    @Mock
//    private ReviewRepository reviewRepository;
//    @Mock
//    private TutorService tutorService;
//    @Mock
//    private LectureService lectureService;
//    @Mock
//    private TutorTagRepository tutorTagRepository;
//    @Mock
//    private UserService userService;
//    @Mock
//    private LectureParticipantService lectureParticipantService;
//    @Mock
//    private TutorCallService tutorCallService;
//
//    @BeforeEach
//    void beforeEach(){
//        this.reviewService = new ReviewService(reviewRepository,lectureService, userService,
// lectureParticipantService, tutorCallService);
//    }
//
//    @Test
//    @DisplayName("유저의 리뷰를 가져올 수 있어야 한다.")
//    void tutorReviews() {
//        Pageable pageMock = mock(Pageable.class);
//
//
//        Map<Long, User> users = new HashMap<>();
//        Map<Long, Review> reviews = new HashMap();
//        Map<Long, Tutor> tutors = new HashMap<>();
//
//        for(long i = 1; i <= 300; i++){
//            users.put(i, User
//                    .builder()
//                    .email(new StringBuilder("test")
//                            .append(i+1)
//                            .append("@gmail.com")
//                            .toString())
//                    .nickname("test" + i)
//                    .profile("test" + i)
//                    .role(i <= 150 ? RoleType.TUTOR : RoleType.USER)// 150까지 TUTOR
//                    .build());
//        }
//
//        long idx = 0;
//        for(long i = 1; i <= 300; i++){
//            if(!users.get(i).getRole().equals(RoleType.TUTOR)) continue;
//            tutors.put(++idx, Tutor.builder()
//                    .introduction("test")
//                    .user(users.get(i))
//                    .build());
//        }
//
//        idx = 0;
//        for(int k = 1 ; k <= tutors.size(); k++){
//            for(int i = 151; i <= users.size(); i++){
//                reviews.put(++idx,
//                        Review.builder()
//                                .reviewer(users.get(i))
//                                .tutor(tutors.get(k))
//                                .communicationRate(4)
//                                .content("content")
//                                .professionalismRate(4)
//                                .mannerRate(1)
//                                .build());
//            }
//        }
//
//    }
// }
