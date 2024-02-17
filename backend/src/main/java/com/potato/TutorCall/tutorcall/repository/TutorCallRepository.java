package com.potato.TutorCall.tutorcall.repository;

import com.potato.TutorCall.tutor.domain.Tutor;
import com.potato.TutorCall.tutorcall.domain.TutorCall;
import com.potato.TutorCall.user.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorCallRepository extends JpaRepository<TutorCall, Long> {
  List<TutorCall> findByUserOrderByIdDesc(User currentUser);

  List<TutorCall> findAllByTutorOrderByIdDesc(Tutor tutor);

}
