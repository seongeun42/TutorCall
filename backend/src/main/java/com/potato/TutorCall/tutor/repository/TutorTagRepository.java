package com.potato.TutorCall.tutor.repository;

import com.potato.TutorCall.tutor.domain.Tutor;
import com.potato.TutorCall.tutor.domain.TutorTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorTagRepository extends JpaRepository<TutorTag, Long> {
    List<TutorTag> findByTutor(Tutor tutor);
}
