package com.potato.TutorCall.mypage.controller;

import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.tutor.domain.Tutor;
import com.potato.TutorCall.tutor.domain.TutorTag;
import com.potato.TutorCall.tutor.domain.enums.SchoolType;
import com.potato.TutorCall.tutor.repository.TagRepository;
import com.potato.TutorCall.tutor.repository.TutorRepository;
import com.potato.TutorCall.tutor.repository.TutorTagRepository;
import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.domain.enums.RoleType;
import com.potato.TutorCall.user.domain.enums.SnsType;
import com.potato.TutorCall.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.yml")
class MyPageControllerTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TutorRepository tutorRepository;
    @Autowired
    private TutorTagRepository tutorTagRepository;
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private WebApplicationContext wc;

    private MockHttpSession session;
    private MockMvc mockMvc;

    @BeforeEach
    void addTestData() {
        User testUser1 = User.builder()
                .email("user1@ssafy.com")
                .nickname("user1")
                .password("pw1")
                .profile("img1.jpg")
                .sns(SnsType.NAVER)
                .point(100)
                .role(RoleType.USER)
                .build();
        userRepository.save(testUser1);

        User testUser2 = User.builder()
                .email("user2@ssafy.com")
                .nickname("user2")
                .password("pw2")
                .profile("img2.jpg")
                .sns(SnsType.KAKAO)
                .point(300)
                .role(RoleType.TUTOR)
                .build();
        Tutor testTutor = Tutor.builder()
                .user(testUser2)
                .introduction("test tutor")
                .build();
        tutorRepository.save(testTutor);
        userRepository.save(testUser2);

        Tag tag1 = Tag.builder()
                .grade(2)
                .level(SchoolType.HIGH)
                .subject("Math")
                .build();
        Tag tag2 = Tag.builder()
                .grade(3)
                .level(SchoolType.HIGH)
                .subject("English")
                .build();
        tagRepository.save(tag1);
        tagRepository.save(tag2);

        TutorTag testTutorTag1 = TutorTag.builder()
                .tutor(testTutor)
                .tag(tag1)
                .build();
        TutorTag testTutorTag2 = TutorTag.builder()
                .tutor(testTutor)
                .tag(tag2)
                .build();
        tutorTagRepository.save(testTutorTag1);
        tutorTagRepository.save(testTutorTag2);

        mockMvc = MockMvcBuilders.webAppContextSetup(wc)
                .build();
    }

    @Test
    @DisplayName("세션 정보가 없으면 정보를 가져올 수 없다")
    void getUserProfileWithoutSession() throws Exception {
        mockMvc.perform(get("/mypage"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("일반 유저의 정보를 가져온다")
    void getUserProfileSuccess() throws Exception {
        session = new MockHttpSession();
        session.setAttribute("user", 1L);

        mockMvc.perform(get("/mypage").session(session))
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
        session = new MockHttpSession();
        session.setAttribute("user", 2L);

        mockMvc.perform(get("/mypage").session(session))
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

    @Test
    @DisplayName("존재하지 않는 유저 정보는 조회할 수 없다")
    void getNonExistentUser() throws Exception {
        session = new MockHttpSession();
        session.setAttribute("user", 3L);

        mockMvc.perform(get("/mypage").session(session))
                .andExpect(status().is4xxClientError());
    }

}