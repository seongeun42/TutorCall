package com.potato.TutorCall.lecture.repository;

import com.potato.TutorCall.lecture.domain.Lecture;
import com.potato.TutorCall.lecture.domain.LectureParticipant;
import com.potato.TutorCall.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureParticipantRepository extends JpaRepository<LectureParticipant, Long> {

    Boolean existsByLectureAndUser(Lecture lecture, User user);

}
