package com.potato.TutorCall.mypage.controller;

import com.potato.TutorCall.auth.SessionKey;
import com.potato.TutorCall.auth.dto.UserSessionDto;
import com.potato.TutorCall.lecture.repository.LectureParticipantRepository;
import com.potato.TutorCall.lecture.repository.LectureRepository;
import com.potato.TutorCall.mypage.datautil.MypageDataInitializer;
import com.potato.TutorCall.review.repository.ReviewRepository;
import com.potato.TutorCall.tutor.domain.enums.SchoolType;
import com.potato.TutorCall.tutor.repository.TagRepository;
import com.potato.TutorCall.tutor.repository.TutorRepository;
import com.potato.TutorCall.tutor.repository.TutorTagRepository;
import com.potato.TutorCall.user.domain.enums.RoleType;
import com.potato.TutorCall.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GetLecturesTest {
  @Autowired private UserRepository userRepository;
  @Autowired private TutorRepository tutorRepository;
  @Autowired private TutorTagRepository tutorTagRepository;
  @Autowired private TagRepository tagRepository;
  @Autowired private LectureRepository lectureRepository;
  @Autowired private ReviewRepository reviewRepository;
  @Autowired private LectureParticipantRepository lectureParticipantRepository;

  @Autowired private MypageDataInitializer dataInitializer;
  @Autowired private WebApplicationContext wc;

  private MockHttpSession session;
  private MockMvc mockMvc;

  @BeforeEach
  void addTestData() throws Exception {
    dataInitializer.addUser(userRepository);
    dataInitializer.addTutor(userRepository, tutorRepository, tutorTagRepository, tagRepository);
    dataInitializer.addExtraTags(tagRepository);
    dataInitializer.addLecture(lectureRepository, lectureParticipantRepository, tagRepository, userRepository, tutorRepository);

    mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();

    UserSessionDto userSession = UserSessionDto.builder().id(1L).roleType(RoleType.USER).build();
    session = new MockHttpSession();
    session.setAttribute(SessionKey.USER, userSession);
  }

  @Test
  @DisplayName("세션 정보가 없으면 강의 정보를 조회할 수 없다")
  void getLectureListWithoutSession() throws Exception {
    mockMvc
            .perform(
                    get("/mypage/lecture").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isForbidden());
  }

  @Test
  @DisplayName("페이지네이션 옵션 없이 조회")
  void getLecture() throws Exception {
    mockMvc
            .perform(
                    get("/mypage/lecture").contentType(MediaType.APPLICATION_JSON).session(session))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.content[0].lectureId").value(2L))
            .andExpect(jsonPath("$.content[0].tutor.id").value(2L))
            .andExpect(jsonPath("$.content[0].tutor.nickname").value("user2"))
            .andExpect(jsonPath("$.content[0].tutor.profile").value("img2.jpg"))
            .andExpect(jsonPath("$.content[0].promotionTitle").value("test2 lecture"))
            .andExpect(jsonPath("$.content[0].promotionState").value(Boolean.FALSE))
            .andExpect(jsonPath("$.content[0].lectureState").value(Boolean.FALSE))
            .andExpect(jsonPath("$.content[0].tag.id").value(3L))
            .andExpect(jsonPath("$.content[0].tag.subject").value("Korean"))
            .andExpect(jsonPath("$.content[0].tag.level").value(SchoolType.ELEMENTARY.name()))
            .andExpect(jsonPath("$.content[0].tag.grade").value(1))
            .andExpect(jsonPath("$.content[1].lectureId").value(1L))
            .andExpect(jsonPath("$.content[1].tutor.id").value(2L))
            .andExpect(jsonPath("$.content[1].tutor.nickname").value("user2"))
            .andExpect(jsonPath("$.content[1].tutor.profile").value("img2.jpg"))
            .andExpect(jsonPath("$.content[1].promotionTitle").value("test1 lecture"))
            .andExpect(jsonPath("$.content[1].promotionState").value(Boolean.FALSE))
            .andExpect(jsonPath("$.content[1].lectureState").value(Boolean.FALSE))
            .andExpect(jsonPath("$.content[1].tag.id").value(1L))
            .andExpect(jsonPath("$.content[1].tag.subject").value("Math"))
            .andExpect(jsonPath("$.content[1].tag.level").value(SchoolType.HIGH.name()))
            .andExpect(jsonPath("$.content[1].tag.grade").value(2));
  }

  @Test
  @DisplayName("페이지네이션 옵션 넣고 조회")
  void getLectureWithPagination() throws Exception {
    for(int i = 0; i < 50; ++i) {
      dataInitializer.addLecture(lectureRepository, lectureParticipantRepository, tagRepository, userRepository, tutorRepository);
    }

    mockMvc
            .perform(
                    get("/mypage/lecture?page=3&size=17").contentType(MediaType.APPLICATION_JSON).session(session))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.pageable.pageNumber").value(3))
            .andExpect(jsonPath("$.pageable.pageSize").value(17));
  }

  @Test
  @DisplayName("리뷰가 있는 강의 목록 조회")
  void getLectureWithReview() throws Exception {
    dataInitializer.addReview(reviewRepository, userRepository, lectureRepository);

    mockMvc
            .perform(
                    get("/mypage/lecture").contentType(MediaType.APPLICATION_JSON).session(session))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.content[0].review").value(Boolean.TRUE))
            .andExpect(jsonPath("$.content[1].review").value(Boolean.FALSE));
  }

}
