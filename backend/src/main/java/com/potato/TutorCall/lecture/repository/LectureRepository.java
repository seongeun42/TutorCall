package com.potato.TutorCall.lecture.repository;

import com.potato.TutorCall.lecture.domain.Lecture;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {

    @Modifying
    @Query(value="UPDATE Lecture l set l.isDelete=:isDelete where l.id=:lectureId")
    int updateLecturerByIdAndisDelete(@Param("lectureId") long lectureId, @Param("isDelete") boolean isDelete);
}
