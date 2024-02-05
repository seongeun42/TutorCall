package com.potato.TutorCall.qna.repository;

import com.potato.TutorCall.qna.domain.Question;
import java.util.Optional;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository
    extends JpaRepository<Question, Long>, QuestionRepositoryCustom {

  @Modifying
  @Query("UPDATE Question q set q.isDelete = :isDelete where q.id = :questionId")
  int deleteQuestion(@Param("questionId") Long questionId, @Param("isDelete") boolean isDelete);

  Optional<Object> findQuestionByIdAndIsDelete(long questionId, boolean isDelete);
}
