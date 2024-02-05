package com.potato.TutorCall.tutor.repository;

import com.potato.TutorCall.tutor.domain.Tag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

  Tag findTagBySubject(String subject);

  List<Tag> findTagsByIdIn(List<Long> idList);
}
