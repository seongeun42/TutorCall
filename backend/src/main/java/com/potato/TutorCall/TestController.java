package com.potato.TutorCall;

import com.potato.TutorCall.lecture.domain.Lecture;
import com.potato.TutorCall.lecture.domain.LectureParticipant;
import com.potato.TutorCall.lecture.repository.LectureParticipantRepository;
import com.potato.TutorCall.lecture.repository.LectureRepository;
import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.tutor.domain.Tutor;
import com.potato.TutorCall.tutor.domain.TutorTag;
import com.potato.TutorCall.tutor.domain.enums.SchoolType;
import com.potato.TutorCall.tutor.service.TagService;
import com.potato.TutorCall.tutor.service.TutorService;
import com.potato.TutorCall.tutor.service.TutorTagService;
import com.potato.TutorCall.tutorcall.domain.TutorCall;
import com.potato.TutorCall.tutorcall.repository.TutorCallRepository;
import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.domain.enums.RoleType;
import com.potato.TutorCall.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final TutorService tutorService;
    private final UserService userService;
    private final TutorTagService tutorTagService;
    private final TagService tagService;
    private final TutorCallRepository tutorCallRepository;
    private final LectureRepository lectureRepository;
    private final LectureParticipantRepository lectureParticipantRepository;

    // Tutor, Tag 데이터 넣기
    @GetMapping("/data")
    public String data() {
        User user = User.builder()
                .email("tutor@test.com")
                .password("1234")
                .role(RoleType.TUTOR)
                .nickname("tutor")
                .build();
        userService.save(user);
        User student1 = User.builder()
                .email("user1@test.com")
                .password("1234")
                .role(RoleType.USER)
                .nickname("testUser1")
                .build();
        userService.save(student1);
        User student2 = User.builder()
                .email("user2@test.com")
                .password("1234")
                .role(RoleType.USER)
                .nickname("testUser2")
                .build();
        userService.save(student2);

        Tutor tutor = Tutor.builder()
                .user(user)
                .introduction("hi")
                .build();
        tutorService.save(tutor);

        Tag tag = null;
        for (int i = 1; i <= 15; i++) {
            tag = Tag.builder()
                    .subject("math")
                    .level(SchoolType.HIGH)
                    .grade(i)
                    .build();
            tagService.save(tag);

            TutorTag tutorTag = TutorTag.builder().tutor(tutor).tag(tag).build();
            tutorTagService.save(tutorTag);
        }

        TutorCall tutorCall = TutorCall.builder()
                .tutor(tutor)
                .user(student1)
                .build();
        tutorCallRepository.save(tutorCall);

        for (int i = 0; i < 5; i++) {
            Lecture lecture = Lecture.builder()
                    .tutor(tutor)
                    .promotionTitle("test" + i)
                    .promotionContent("testtest" + i)
                    .maxParticipants(2 + i)
                    .price(100)
                    .tag(tag)
                    .build();
            lectureRepository.save(lecture);

            LectureParticipant lectureParticipant = LectureParticipant.builder()
                    .lecture(lecture)
                    .user(student1)
                    .build();
            lectureParticipantRepository.save(lectureParticipant);
        }


        return "OK";
    }

}