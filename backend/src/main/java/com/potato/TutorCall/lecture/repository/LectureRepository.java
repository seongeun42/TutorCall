package com.potato.TutorCall.lecture.repository;

import com.potato.TutorCall.lecture.domain.Lecture;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {

  /**
   * Lecture 삭제 상태 변경 함수
   *
   * @param lectureId
   * @param isDelete
   * @return 변경된 레코드 수
   */
  @Modifying
  @Query(value = "UPDATE Lecture l set l.isDelete=:isDelete where l.id=:lectureId")
  int deleteLecture(@Param("lectureId") long lectureId, @Param("isDelete") boolean isDelete);

  /**
   * 마감 기한 지난 Lecture의 모집 마감 상태 변경 함수
   *
   * @param start
   * @param end
   */
  @Modifying
  @Query(
      value =
          "update Lecture l set l.promotionState = true where l.promotionDue between :start and"
              + " :end")
  void changeLecturePromotionState(
      @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

  /**
   * 종료일이 지난 Lecture의 과외 상태 변경 함수
   *
   * @param start
   * @param end
   */
  @Modifying
  @Query(
      value =
          "update Lecture l set l.lectureState = true where l.lectureEndAt between :start and :end")
  void changeLectureState(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
