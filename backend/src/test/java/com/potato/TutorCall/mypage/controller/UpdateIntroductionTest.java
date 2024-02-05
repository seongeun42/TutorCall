package com.potato.TutorCall.mypage.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.potato.TutorCall.auth.SessionKey;
import com.potato.TutorCall.auth.dto.UserSessionDto;
import com.potato.TutorCall.mypage.datautil.MypageDataInitializer;
import com.potato.TutorCall.tutor.repository.TagRepository;
import com.potato.TutorCall.tutor.repository.TutorRepository;
import com.potato.TutorCall.tutor.repository.TutorTagRepository;
import com.potato.TutorCall.user.domain.enums.RoleType;
import com.potato.TutorCall.user.repository.UserRepository;
import java.util.HashMap;
import java.util.Map;
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
public class UpdateIntroductionTest {
  @Autowired private UserRepository userRepository;
  @Autowired private TutorRepository tutorRepository;
  @Autowired private TutorTagRepository tutorTagRepository;
  @Autowired private TagRepository tagRepository;
  @Autowired private MypageDataInitializer dataInitializer;

  @Autowired private WebApplicationContext wc;

  private MockHttpSession session;
  private MockMvc mockMvc;
  private String requestBody;

  @BeforeEach
  void addTestData() throws Exception {
    dataInitializer.addUser(userRepository);
    dataInitializer.addTutor(userRepository, tutorRepository, tutorTagRepository, tagRepository);

    mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();

    UserSessionDto userSession = UserSessionDto.builder().id(2L).roleType(RoleType.TUTOR).build();
    session = new MockHttpSession();
    session.setAttribute(SessionKey.USER, userSession);

    Map<String, String> requestMap = new HashMap<>();
    requestMap.put("tags", "test introduction");
    requestBody = new ObjectMapper().writeValueAsString(requestMap);
  }

  @Test
  @DisplayName("세션 정보가 없으면 소개글을 갱신할 수 없다")
  void updateIntroductionWithoutSession() throws Exception {
    mockMvc
        .perform(
            patch("/mypage/tutor/intro")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
        .andExpect(status().isForbidden());
  }

  @Test
  @DisplayName("세션의 유저 타입이 선생이 아니면 소개글을 갱신할 수 없다")
  void updateIntroductionWithInvalidRole() throws Exception {
    UserSessionDto userSession = UserSessionDto.builder().id(1L).roleType(RoleType.USER).build();
    session = new MockHttpSession();
    session.setAttribute(SessionKey.USER, userSession);

    mockMvc
        .perform(
            patch("/mypage/tutor/intro")
                .session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
        .andExpect(status().isForbidden());
  }

  @Test
  @DisplayName("소개글 갱신 성공")
  void updateIntroductionSuccess() throws Exception {
    mockMvc
        .perform(
            patch("/mypage/tutor/intro")
                .session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
        .andExpect(status().isOk());
  }
}
