package com.potato.TutorCall.lecture.repository;

import com.potato.TutorCall.lecture.domain.Lecture;
import com.potato.TutorCall.lecture.domain.LectureParticipant;
import com.potato.TutorCall.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureParticipantRepository extends JpaRepository<LectureParticipant, Long> {

    /**
     * 과외 참가자가 맞는지 확인하는 함수
     * @param lecture
     * @param user
     * @return Boolean
     */
    Boolean existsByLectureAndUser(Lecture lecture, User user);

    /**
     * 특정 과외에 참여 목록 전체 삭제
     * @param lecture
     */
    @Modifying
    @Query(value = "delete from LectureParticipant lp where lp.lecture = :lecture")
    void deleteAllByLecture(@Param("lecture") Lecture lecture);

}
