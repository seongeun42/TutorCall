package com.potato.TutorCall.mypage.datautil;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/** Mypage 테스트용 데이터 추가를 위한 클래스 */
@Component
public class MypageDataInitializer {
  @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

  public void addUser(UserRepository userRepository) {
    User testUser =
        User.builder()
            .email("user1@ssafy.com")
            .nickname("user1")
            .password(bCryptPasswordEncoder.encode("pw1"))
            .profile("img1.jpg")
            .sns(SnsType.NAVER)
            .point(100)
            .role(RoleType.USER)
            .build();
    userRepository.save(testUser);
  }

  public void addTutor(
      UserRepository userRepository,
      TutorRepository tutorRepository,
      TutorTagRepository tutorTagRepository,
      TagRepository tagRepository) {
    User testUser =
        User.builder()
            .email("user2@ssafy.com")
            .nickname("user2")
            .password(bCryptPasswordEncoder.encode("pw2"))
            .profile("img2.jpg")
            .sns(SnsType.KAKAO)
            .point(300)
            .role(RoleType.TUTOR)
            .build();
    Tutor testTutor = Tutor.builder().user(testUser).introduction("test tutor").build();
    tutorRepository.save(testTutor);
    userRepository.save(testUser);

    Tag tag1 = Tag.builder().grade(2).level(SchoolType.HIGH).subject("Math").build();
    Tag tag2 = Tag.builder().grade(3).level(SchoolType.HIGH).subject("English").build();
    tagRepository.save(tag1);
    tagRepository.save(tag2);

    TutorTag testTutorTag1 = TutorTag.builder().tutor(testTutor).tag(tag1).build();
    TutorTag testTutorTag2 = TutorTag.builder().tutor(testTutor).tag(tag2).build();
    tutorTagRepository.save(testTutorTag1);
    tutorTagRepository.save(testTutorTag2);
  }

  public void addExtraTags(TagRepository tagRepository) {
    Tag tag3 = Tag.builder().grade(1).level(SchoolType.ELEMENTARY).subject("Korean").build();
    Tag tag4 = Tag.builder().grade(3).level(SchoolType.MIDDLE).subject("English").build();
    tagRepository.save(tag3);
    tagRepository.save(tag4);
  }
}
