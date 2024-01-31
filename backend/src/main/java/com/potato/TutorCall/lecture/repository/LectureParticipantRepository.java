package com.potato.TutorCall.lecture.repository;

import com.potato.TutorCall.lecture.domain.Lecture;
import com.potato.TutorCall.lecture.domain.LectureParticipant;
import com.potato.TutorCall.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureParticipantRepository extends JpaRepository<LectureParticipant, Long> {

    /**
     * 과외 참가자가 맞는지 확인하는 함수
     * @param lectureId
     * @param userId
     * @return Boolean
     */
    @Query(value = "select count(lp) > 0 from LectureParticipant lp where lp.lecture.id = :lectureId and lp.user.id = :userId")
    Boolean existByLectureAndUser(@Param(value = "lectureId") Long lectureId, @Param(value = "userId") Long userId);

    /**
     * 특정 과외에 참여 목록 전체 삭제
     * @param lecture
     */
    @Modifying
    @Query(value = "delete from LectureParticipant lp where lp.lecture = :lecture")
    void deleteAllByLecture(@Param("lecture") Lecture lecture);

    /**
     * 과외 신청 삭제
     * @param lectureId
     * @param userId
     */
    @Modifying
    @Query(value = "delete from LectureParticipant lp where lp.lecture.id = :lectureId and  lp.user.id = :userId")
    void deleteByLectureAndUser(@Param(value = "lectureId") Long lectureId, @Param(value = "userId") Long userId);

    /**
     * 사용자가 참여한 과외 정보를 가져옴
     * @param user
     * @return
     */
    List<LectureParticipant> findByUser(User user);
}
