package com.potato.TutorCall.review.repository;

import com.potato.TutorCall.review.domain.Review;
import com.potato.TutorCall.tutor.domain.Tutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    /**
     * Tutor에게 달린 평가의 매너 점수의 평균
     * @param tutor
     * @return Tutor의 매너 점수
     */
    @Query(value = "select avg(r.mannerRate) from Review r where r.tutor = :tutor")
    double getTutorMannerAvg(@Param("tutor") Tutor tutor);

    /**
     * Tutor에게 달린 평가의 전달력 점수의 평균
     * @param tutor
     * @return Tutor의 전달력 점수
     */
    @Query(value = "select avg(r.communicationRate) from Review r where r.tutor = :tutor")
    double getTutorCommnunicationAvg(@Param("tutor") Tutor tutor);

    /**
     * Tutor에게 달린 평가의 전문성 점수의 평균
     * @param tutor
     * @return Tutor의 전문성 점수
     */
    @Query(value = "select avg(r.professionalismRate) from Review r where r.tutor = :tutor")
    double getTutorProfessionalismAvg(@Param("tutor") Tutor tutor);

    Page<Review> findReviewsByTutor_IdAndCreatedAtBetweenOrderByCreatedAtDesc(Long tutorId, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
