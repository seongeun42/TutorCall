package com.potato.TutorCall.mypage.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.potato.TutorCall.auth.SessionKey;
import com.potato.TutorCall.auth.dto.UserSessionDto;
import com.potato.TutorCall.mypage.datautil.MypageDataInitializer;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GetUserProfileTest {
  @Autowired private UserRepository userRepository;
  @Autowired private TutorRepository tutorRepository;
  @Autowired private TutorTagRepository tutorTagRepository;
  @Autowired private TagRepository tagRepository;
  @Autowired private MypageDataInitializer dataInitializer;

  @Autowired private WebApplicationContext wc;

  private MockHttpSession session;
  private MockMvc mockMvc;

  @BeforeEach
  void addTestData() {
    dataInitializer.addUser(userRepository);
    dataInitializer.addTutor(userRepository, tutorRepository, tutorTagRepository, tagRepository);

    mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();
  }

  @Test
  @DisplayName("세션 정보가 없으면 정보를 가져올 수 없다")
  void getUserProfileWithoutSession() throws Exception {
    mockMvc.perform(get("/mypage")).andExpect(status().isForbidden());
  }

  @Test
  @DisplayName("일반 유저의 정보를 가져온다")
  void getUserProfileSuccess() throws Exception {
    UserSessionDto userSession = UserSessionDto.builder().id(1L).roleType(RoleType.USER).build();

    session = new MockHttpSession();
    session.setAttribute(SessionKey.USER, userSession);

    mockMvc
        .perform(get("/mypage").session(session))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.userId").value(1L))
        .andExpect(jsonPath("$.role").value(RoleType.USER.name()))
        .andExpect(jsonPath("$.email").value("user1@ssafy.com"))
        .andExpect(jsonPath("$.point").value(100))
        .andExpect(jsonPath("$.profile").value("img1.jpg"));
  }

  @Test
  @DisplayName("선생의 정보를 가져온다")
  void getTutorProfileSuccess() throws Exception {
    UserSessionDto userSession = UserSessionDto.builder().id(2L).roleType(RoleType.TUTOR).build();

    session = new MockHttpSession();
    session.setAttribute(SessionKey.USER, userSession);

    mockMvc
        .perform(get("/mypage").session(session))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.userId").value(2L))
        .andExpect(jsonPath("$.role").value(RoleType.TUTOR.name()))
        .andExpect(jsonPath("$.email").value("user2@ssafy.com"))
        .andExpect(jsonPath("$.point").value(300))
        .andExpect(jsonPath("$.profile").value("img2.jpg")) // 여기까지 공통 부분
        .andExpect(jsonPath("$.tutor.introduction").value("test tutor"))
        .andExpect(jsonPath("$.tutor.reliability").value(0))
        .andExpect(jsonPath("$.tutor.mannerRate").value(0.0))
        .andExpect(jsonPath("$.tutor.communicationRate").value(0.0))
        .andExpect(jsonPath("$.tutor.professionalismRate").value(0.0))
        .andExpect(jsonPath("$.tutor.tags[0].subject").value("Math"))
        .andExpect(jsonPath("$.tutor.tags[0].level").value(SchoolType.HIGH.name()))
        .andExpect(jsonPath("$.tutor.tags[0].grade").value(2))
        .andExpect(jsonPath("$.tutor.tags[1].subject").value("English"))
        .andExpect(jsonPath("$.tutor.tags[1].level").value(SchoolType.HIGH.name()))
        .andExpect(jsonPath("$.tutor.tags[1].grade").value(3));
  }
}
