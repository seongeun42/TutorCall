package com.potato.TutorCall.tutor.repository;

import com.potato.TutorCall.tutor.domain.Tutor;
import com.potato.TutorCall.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {
  Optional<Tutor> findByUser(User user);
}
