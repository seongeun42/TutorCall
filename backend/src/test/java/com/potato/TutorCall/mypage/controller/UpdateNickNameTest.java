package com.potato.TutorCall.mypage.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.potato.TutorCall.mypage.datautil.MypageDataInitializer;
import com.potato.TutorCall.tutor.repository.TagRepository;
import com.potato.TutorCall.tutor.repository.TutorRepository;
import com.potato.TutorCall.tutor.repository.TutorTagRepository;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UpdateNickNameTest {
  @Autowired private UserRepository userRepository;
  @Autowired private TutorRepository tutorRepository;
  @Autowired private TutorTagRepository tutorTagRepository;
  @Autowired private TagRepository tagRepository;

  @Autowired private WebApplicationContext wc;

  private MockHttpSession session;
  private MockMvc mockMvc;

  @BeforeEach
  void addTestData() {
    MypageDataInitializer.addUser(userRepository);
    MypageDataInitializer.addTutor(
        userRepository, tutorRepository, tutorTagRepository, tagRepository);

    mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();
  }

  @Test
  @DisplayName("세션 정보가 없으면 닉네임을 변경할 수 없다")
  void updateNickNameWithoutSession() throws Exception {
    Map<String, String> requestMap = new HashMap<>();
    requestMap.put("nickname", "new_nickname");
    String requestBody = new ObjectMapper().writeValueAsString(requestMap);

    mockMvc
        .perform(
            patch("/mypage/nickname").contentType(MediaType.APPLICATION_JSON).content(requestBody))
        .andExpect(status().is5xxServerError());
  }

  @Test
  @DisplayName("사용자는 본인의 닉네임을 변경할 수 있음")
  void updateNickname() throws Exception {
    session = new MockHttpSession();
    session.setAttribute("user", 1L);

    Map<String, String> requestMap = new HashMap<>();
    requestMap.put("nickname", "new_nickname");
    String requestBody = new ObjectMapper().writeValueAsString(requestMap);

    mockMvc
        .perform(
            patch("/mypage/nickname")
                .session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
        .andExpect(status().isOk());

    String updateNickname = userRepository.findById(1L).get().getNickname();
    assertThat(updateNickname).isEqualTo("new_nickname");
  }
}
