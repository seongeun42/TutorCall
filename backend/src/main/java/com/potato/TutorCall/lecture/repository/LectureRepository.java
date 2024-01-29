package com.potato.TutorCall.lecture.repository;

import com.potato.TutorCall.lecture.domain.Lecture;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {

    /**
     * 과외 상태를 삭제 상태로 변경하는 함수
     * @param lectureId
     * @param isDelete
     * @return 수정된 레코드 개수
     */
    @Modifying
    @Query(value="update Lecture l set l.isDelete = :isDelete where l.id = :lectureId")
    int deleteLecture(@Param("lectureId") long lectureId, @Param("isDelete") boolean isDelete);

}

