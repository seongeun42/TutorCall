package com.potato.TutorCall.user.repository;

import com.potato.TutorCall.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByNickname(String nickname);
    User findByEmail(String email);


}
