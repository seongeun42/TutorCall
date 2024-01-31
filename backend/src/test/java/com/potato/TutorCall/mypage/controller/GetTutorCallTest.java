package com.potato.TutorCall.mypage.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.potato.TutorCall.auth.SessionKey;
import com.potato.TutorCall.auth.dto.UserSessionDto;
import com.potato.TutorCall.mypage.datautil.MypageDataInitializer;
import com.potato.TutorCall.review.repository.ReviewRepository;
import com.potato.TutorCall.tutor.repository.TagRepository;
import com.potato.TutorCall.tutor.repository.TutorRepository;
import com.potato.TutorCall.tutor.repository.TutorTagRepository;
import com.potato.TutorCall.tutorcall.repository.TutorCallRepository;
import com.potato.TutorCall.user.domain.enums.RoleType;
import com.potato.TutorCall.user.repository.UserRepository;
import org.hamcrest.core.IsNull;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GetTutorCallTest {
  @Autowired private UserRepository userRepository;
  @Autowired private TutorRepository tutorRepository;
  @Autowired private TutorTagRepository tutorTagRepository;
  @Autowired private TagRepository tagRepository;
  @Autowired private ReviewRepository reviewRepository;
  @Autowired private TutorCallRepository tutorCallRepository;

  @Autowired private MypageDataInitializer dataInitializer;
  @Autowired private WebApplicationContext wc;

  private MockHttpSession session;
  private MockMvc mockMvc;

  @BeforeEach
  void addTestData() throws Exception {
    dataInitializer.addUser(userRepository);
    dataInitializer.addTutor(userRepository, tutorRepository, tutorTagRepository, tagRepository);
    dataInitializer.addExtraTags(tagRepository);
    dataInitializer.addTutorCall(
        tutorCallRepository, tutorRepository, userRepository, tagRepository);

    mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();

    UserSessionDto userSession = UserSessionDto.builder().id(1L).roleType(RoleType.USER).build();
    session = new MockHttpSession();
    session.setAttribute(SessionKey.USER, userSession);
  }

  @Test
  @DisplayName("세션 정보가 없으면 튜터콜 정보를 조회할 수 없다")
  void getTutoCallListWithoutSession() throws Exception {
    mockMvc
        .perform(get("/mypage/tutorCall").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isForbidden());
  }

  @Test
  @DisplayName("페이지네이션 옵션 없이 조회")
  void getTutorCall() throws Exception {
    mockMvc
        .perform(get("/mypage/tutorCall").contentType(MediaType.APPLICATION_JSON).session(session))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.content[0].tutoringId").value(2L))
        .andExpect(jsonPath("$.content[0].tutor.id").value(2L))
        .andExpect(jsonPath("$.content[0].tutor.nickname").value("user2"))
        .andExpect(jsonPath("$.content[0].tutor.profile").value("img2.jpg"))
        .andExpect(jsonPath("$.content[0].user.id").value(1L))
        .andExpect(jsonPath("$.content[0].user.nickname").value("user1"))
        .andExpect(jsonPath("$.content[0].user.profile").value("img1.jpg"))
        .andExpect(jsonPath("$.content[0].problem").value("content2"))
        .andExpect(jsonPath("$.content[0].replayVideo").value("replay2"))
        .andExpect(jsonPath("$.content[0].liveUrl").value("liveUrl2"))
        .andExpect(jsonPath("$.content[0].liveState").value(Boolean.TRUE))
        .andExpect(jsonPath("$.content[0].price").value(2000))
        .andExpect(jsonPath("$.content[1].tutoringId").value(1L))
        .andExpect(jsonPath("$.content[1].tutor.id").value(2L))
        .andExpect(jsonPath("$.content[1].tutor.nickname").value("user2"))
        .andExpect(jsonPath("$.content[1].tutor.profile").value("img2.jpg"))
        .andExpect(jsonPath("$.content[1].user.id").value(1L))
        .andExpect(jsonPath("$.content[1].user.nickname").value("user1"))
        .andExpect(jsonPath("$.content[1].user.profile").value("img1.jpg"))
        .andExpect(jsonPath("$.content[1].problem").value("content1"))
        .andExpect(jsonPath("$.content[1].replayVideo").value("replay1"))
        .andExpect(jsonPath("$.content[1].liveUrl").value("liveUrl1"))
        .andExpect(jsonPath("$.content[1].liveState").value(Boolean.FALSE))
        .andExpect(jsonPath("$.content[1].price").value(1000));
  }

  @Test
  @DisplayName("페이지네이션 옵션 넣고 조회")
  void getTutorCallWithPagination() throws Exception {
    for (int i = 0; i < 50; ++i) {
      dataInitializer.addTutorCall(
          tutorCallRepository, tutorRepository, userRepository, tagRepository);
    }

    mockMvc
        .perform(
            get("/mypage/tutorCall?page=3&size=17")
                .contentType(MediaType.APPLICATION_JSON)
                .session(session))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.pageable.pageNumber").value(3))
        .andExpect(jsonPath("$.pageable.pageSize").value(17));
  }

  @Test
  @DisplayName("리뷰가 있는 튜터콜 목록 조회")
  void getTutorCallWithReview() throws Exception {
    dataInitializer.addTutorCallReview(reviewRepository, userRepository, tutorCallRepository);

    mockMvc
        .perform(get("/mypage/tutorCall").contentType(MediaType.APPLICATION_JSON).session(session))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.content[0].review.mannerRate").value(3))
        .andExpect(jsonPath("$.content[0].review.communicationRate").value(2))
        .andExpect(jsonPath("$.content[0].review.professionalismRate").value(5))
        .andExpect(jsonPath("$.content[0].review.content").value("test content"))
        .andExpect(jsonPath("$.content[1].review").value(IsNull.nullValue()));
  }
}
