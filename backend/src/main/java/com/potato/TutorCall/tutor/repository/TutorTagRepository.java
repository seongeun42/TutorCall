package com.potato.TutorCall.tutor.repository;

import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.tutor.domain.Tutor;
import com.potato.TutorCall.tutor.domain.TutorTag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorTagRepository extends JpaRepository<TutorTag, Long> {

  List<TutorTag> findByTutor(Tutor tutor);

  @Query(value = "select tt.tag from TutorTag tt join tt.tag Where tt.tutor = :tutor")
  List<Tag> getTagsByTutor(@Param("tutor") Tutor tutor);
}
