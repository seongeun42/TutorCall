package com.potato.TutorCall.mypage.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.potato.TutorCall.auth.SessionKey;
import com.potato.TutorCall.auth.dto.UserSessionDto;
import com.potato.TutorCall.mypage.datautil.MypageDataInitializer;
import com.potato.TutorCall.tutor.domain.Tutor;
import com.potato.TutorCall.tutor.domain.TutorTag;
import com.potato.TutorCall.tutor.repository.TagRepository;
import com.potato.TutorCall.tutor.repository.TutorRepository;
import com.potato.TutorCall.tutor.repository.TutorTagRepository;
import com.potato.TutorCall.user.domain.enums.RoleType;
import com.potato.TutorCall.user.repository.UserRepository;
import java.util.*;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UpdateTagListTest {
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
    dataInitializer.addExtraTags(tagRepository);

    mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();

    UserSessionDto userSession = UserSessionDto.builder().id(2L).roleType(RoleType.TUTOR).build();
    session = new MockHttpSession();
    session.setAttribute(SessionKey.USER, userSession);

    List<Long> newTagIds = new ArrayList<>();
    newTagIds.add(3L);
    newTagIds.add(4L);

    Map<String, List> requestMap = new HashMap<>();
    requestMap.put("tags", newTagIds);
    requestBody = new ObjectMapper().writeValueAsString(requestMap);
  }

  @Test
  @DisplayName("세션 정보가 없으면 태그를 갱신할 수 없다")
  void updateTagListWithoutSession() throws Exception {
    mockMvc
        .perform(
            put("/mypage/tutor/tag").contentType(MediaType.APPLICATION_JSON).content(requestBody))
        .andExpect(status().isForbidden());
  }

  @Test
  @DisplayName("세션의 유저 타입이 선생이 아니면 태그를 갱신할 수 없다")
  void updateTagListWithInvalidRole() throws Exception {
    UserSessionDto userSession = UserSessionDto.builder().id(1L).roleType(RoleType.USER).build();
    session = new MockHttpSession();
    session.setAttribute(SessionKey.USER, userSession);

    mockMvc
        .perform(
            put("/mypage/tutor/tag")
                .session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
        .andExpect(status().isForbidden());
  }

  @Test
  @DisplayName("Tag 테이블에 없는 태그로 갱신할 수 없다.")
  void updateTagListWithInvalidTag() throws Exception {
    List<Long> newTagIds = new ArrayList<>();
    newTagIds.add(5L);
    newTagIds.add(6L);

    Map<String, List> requestMap = new HashMap<>();
    requestMap.put("tags", newTagIds);
    requestBody = new ObjectMapper().writeValueAsString(requestMap);

    mockMvc
        .perform(
            put("/mypage/tutor/tag")
                .session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("태그 개수는 유지하면서 모든 태그가 다 바뀌는 경우")
  void updateTagScenario1() throws Exception {
    mockMvc
        .perform(
            put("/mypage/tutor/tag")
                .session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
        .andExpect(status().isOk());

    Tutor currentTutor = tutorRepository.findById(2L).get();
    List<TutorTag> tutorTagList = currentTutor.getTutorTagList();

    Collections.sort(tutorTagList, Comparator.comparing((TutorTag t) -> t.getTag().getId()));

    assertThat(tutorTagList).hasSize(2);
    assertThat(tutorTagList.get(0).getTag().getId()).isEqualTo(3L);
    assertThat(tutorTagList.get(1).getTag().getId()).isEqualTo(4L);
  }

  @Test
  @Transactional
  @DisplayName("태그 개수가 줄면서 모든 태그가 바뀌는 경우")
  void updateTagScenario2() throws Exception {
    List<Long> newTagIds = new ArrayList<>();
    newTagIds.add(3L);

    Map<String, List> requestMap = new HashMap<>();
    requestMap.put("tags", newTagIds);
    requestBody = new ObjectMapper().writeValueAsString(requestMap);

    mockMvc
        .perform(
            put("/mypage/tutor/tag")
                .session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
        .andExpect(status().isOk());

    Tutor currentTutor = tutorRepository.findById(2L).get();
    List<TutorTag> tutorTagList = currentTutor.getTutorTagList();

    Collections.sort(tutorTagList, Comparator.comparing((TutorTag t) -> t.getTag().getId()));

    assertThat(tutorTagList).hasSize(1);
    assertThat(tutorTagList.get(0).getTag().getId()).isEqualTo(3L);
  }

  @Test
  @Transactional
  @DisplayName("태그 개수가 유지되면서 일부가 바뀌는 경우")
  void updateTagScenario3() throws Exception {
    List<Long> newTagIds = new ArrayList<>();
    newTagIds.add(1L);
    newTagIds.add(3L);

    Map<String, List> requestMap = new HashMap<>();
    requestMap.put("tags", newTagIds);
    requestBody = new ObjectMapper().writeValueAsString(requestMap);

    mockMvc
        .perform(
            put("/mypage/tutor/tag")
                .session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
        .andExpect(status().isOk());

    Tutor currentTutor = tutorRepository.findById(2L).get();
    List<TutorTag> tutorTagList = currentTutor.getTutorTagList();

    Collections.sort(tutorTagList, Comparator.comparing((TutorTag t) -> t.getTag().getId()));

    assertThat(tutorTagList).hasSize(2);
    assertThat(tutorTagList.get(0).getTag().getId()).isEqualTo(1L);
    assertThat(tutorTagList.get(1).getTag().getId()).isEqualTo(3L);
  }

  @Test
  @Transactional
  @DisplayName("태그 가 추가되는 경우")
  void updateTagScenario4() throws Exception {
    List<Long> newTagIds = new ArrayList<>();
    newTagIds.add(1L);
    newTagIds.add(2L);
    newTagIds.add(3L);
    newTagIds.add(4L);

    Map<String, List> requestMap = new HashMap<>();
    requestMap.put("tags", newTagIds);
    requestBody = new ObjectMapper().writeValueAsString(requestMap);

    mockMvc
        .perform(
            put("/mypage/tutor/tag")
                .session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
        .andExpect(status().isOk());

    Tutor currentTutor = tutorRepository.findById(2L).get();
    List<TutorTag> tutorTagList = currentTutor.getTutorTagList();

    Collections.sort(tutorTagList, Comparator.comparing((TutorTag t) -> t.getTag().getId()));

    assertThat(tutorTagList).hasSize(4);
    assertThat(tutorTagList.get(0).getTag().getId()).isEqualTo(1L);
    assertThat(tutorTagList.get(1).getTag().getId()).isEqualTo(2L);
    assertThat(tutorTagList.get(2).getTag().getId()).isEqualTo(3L);
    assertThat(tutorTagList.get(3).getTag().getId()).isEqualTo(4L);
  }
}
