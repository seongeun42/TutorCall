package com.potato.TutorCall.mypage.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.potato.TutorCall.auth.SessionKey;
import com.potato.TutorCall.auth.dto.UserSessionDto;
import com.potato.TutorCall.mypage.datautil.MypageDataInitializer;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UpdateProfileTest {
  @Autowired private UserRepository userRepository;
  @Autowired private MypageDataInitializer dataInitializer;

  @Autowired private WebApplicationContext wc;

  private MockHttpSession session;
  private MockMvc mockMvc;

  @BeforeEach
  void addTestData() {
    dataInitializer.addUser(userRepository);

    mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();
  }

  @Test
  @DisplayName("세션 정보가 없으면 프로필 이미지를 변경할 수 없다")
  void updateProfileWithoutSession() throws Exception {
    Map<String, String> requestMap = new HashMap<>();
    requestMap.put("profile", "new_img.jpg");
    String requestBody = new ObjectMapper().writeValueAsString(requestMap);

    mockMvc
        .perform(
            patch("/mypage/profile").contentType(MediaType.APPLICATION_JSON).content(requestBody))
        .andExpect(status().isForbidden());
  }

  @Test
  @DisplayName("사용자는 본인의 프로필 이미지를 변경할 수 있음")
  void updateProfile() throws Exception {
    UserSessionDto userSession = UserSessionDto.builder().id(1L).roleType(RoleType.USER).build();

    session = new MockHttpSession();
    session.setAttribute(SessionKey.USER, userSession);

    Map<String, String> requestMap = new HashMap<>();
    requestMap.put("profile", "new_img.jpg");
    String requestBody = new ObjectMapper().writeValueAsString(requestMap);

    mockMvc
        .perform(
            patch("/mypage/profile")
                .session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
        .andExpect(status().isOk());

    String updateProfile = userRepository.findById(1L).get().getProfile();
    assertThat(updateProfile).isEqualTo("new_img.jpg");
  }
}
