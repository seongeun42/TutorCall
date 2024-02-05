package com.potato.TutorCall.user.repository;

import com.potato.TutorCall.user.domain.User;
import java.time.LocalDateTime;
import java.util.Optional;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  User findByNickname(String nickname);

  @Modifying
  @Query(value = "UPDATE User u set u.block = :block where u.id = :userId")
  int updateUserByIdAndblock(@Param("userId") long userId, @Param("block") LocalDateTime block);

  Boolean existsByEmail(String email);
}
