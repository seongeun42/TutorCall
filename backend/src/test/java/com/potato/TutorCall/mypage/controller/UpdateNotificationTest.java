package com.potato.TutorCall.mypage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.potato.TutorCall.auth.SessionKey;
import com.potato.TutorCall.auth.dto.UserSessionDto;
import com.potato.TutorCall.mypage.datautil.MypageDataInitializer;
import com.potato.TutorCall.user.domain.User;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UpdateNotificationTest {
    @Autowired
    private UserRepository userRepository;
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
    @DisplayName("세션 정보가 없으면 알림 정보를 변경할 수 없다")
    void updateNotiWithoutSession() throws Exception {
        Map<String, Boolean> requestMap = new HashMap<>();
        requestMap.put("notificationOption", Boolean.TRUE);
        String requestBody = new ObjectMapper().writeValueAsString(requestMap);

        mockMvc
                .perform(
                        patch("/mypage/notification").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("알림 정보 변경 성공")
    void updateNotificationOption() throws Exception {
        UserSessionDto userSession = UserSessionDto.builder().id(1L).roleType(RoleType.USER).build();

        session = new MockHttpSession();
        session.setAttribute(SessionKey.USER, userSession);

        Map<String, Boolean> requestMap = new HashMap<>();
        requestMap.put("notificationOption", Boolean.TRUE);
        String requestBody = new ObjectMapper().writeValueAsString(requestMap);

        mockMvc
                .perform(
                        patch("/mypage/notification").session(session).contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isOk());

        User currentUser =  userRepository.findById(1L).get();
        assertThat(currentUser.isNoPushNotification()).isTrue();

        requestMap.put("notificationOption", Boolean.FALSE);
        requestBody = new ObjectMapper().writeValueAsString(requestMap);

        mockMvc
                .perform(
                        patch("/mypage/notification").session(session).contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isOk());

        currentUser =  userRepository.findById(1L).get();
        assertThat(currentUser.isNoPushNotification()).isFalse();
    }

}
