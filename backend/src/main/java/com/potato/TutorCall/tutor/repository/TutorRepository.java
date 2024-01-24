package com.potato.TutorCall.tutor.repository;

import com.potato.TutorCall.tutor.domain.Tutor;
import com.potato.TutorCall.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {
    Optional<Tutor> findByUser(User user);
}