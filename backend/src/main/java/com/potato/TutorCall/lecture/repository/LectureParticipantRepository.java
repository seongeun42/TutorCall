package com.potato.TutorCall.lecture.repository;

import com.potato.TutorCall.lecture.domain.LectureParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureParticipantRepository extends JpaRepository<LectureParticipant, Long> {
}
