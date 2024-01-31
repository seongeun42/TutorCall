package com.potato.TutorCall.tutorcall.repository;

import com.potato.TutorCall.tutorcall.domain.TutorCall;
import com.potato.TutorCall.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorCallRepository extends JpaRepository<TutorCall, Long> {
  List<TutorCall> findByUser(User currentUser);
}
