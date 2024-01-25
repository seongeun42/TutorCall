package com.potato.TutorCall.mypage.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.potato.TutorCall.mypage.datautil.MypageDataInitializer;
import com.potato.TutorCall.user.domain.User;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UpdatePasswordTest {
  @Autowired private UserRepository userRepository;
  @Autowired private MypageDataInitializer dataInitializer;
  @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired private WebApplicationContext wc;

  private MockHttpSession session;
  private MockMvc mockMvc;

  @BeforeEach
  void addTestData() {
    dataInitializer.addUser(userRepository);

    mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();
  }

  @Test
  @DisplayName("세션 정보가 없으면 비밀번호를 변경할 수 없다")
  void updatePasswordWithoutSession() throws Exception {
    Map<String, String> requestMap = new HashMap<>();
    requestMap.put("password", "pw1");
    requestMap.put("newPassword", "npw1");
    String requestBody = new ObjectMapper().writeValueAsString(requestMap);

    mockMvc
        .perform(
            patch("/mypage/password").contentType(MediaType.APPLICATION_JSON).content(requestBody))
        .andExpect(status().isForbidden());
  }

  @Test
  @DisplayName("비밀번호가 일치하지 않으면 변경할 수 없다")
  void passwordInconsistency() throws Exception {
    session = new MockHttpSession();
    session.setAttribute("user", 1L);

    Map<String, String> requestMap = new HashMap<>();
    requestMap.put("password", "pw2");
    requestMap.put("newPassword", "npw1");
    String requestBody = new ObjectMapper().writeValueAsString(requestMap);

    mockMvc
        .perform(
            patch("/mypage/password")
                .session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
        .andExpect(status().isUnauthorized());
  }

  @Test
  @DisplayName("비밀번호 변경 성공")
  void updatePassword() throws Exception {
    session = new MockHttpSession();
    session.setAttribute("user", 1L);

    Map<String, String> requestMap = new HashMap<>();
    requestMap.put("password", "pw1");
    requestMap.put("newPassword", "npw1");
    String requestBody = new ObjectMapper().writeValueAsString(requestMap);

    mockMvc
        .perform(
            patch("/mypage/password")
                .session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
        .andExpect(status().isOk());

    User currentUser = userRepository.findById(1L).get();

    assertThat(bCryptPasswordEncoder.matches("npw1", currentUser.getPassword())).isTrue();
  }
}
