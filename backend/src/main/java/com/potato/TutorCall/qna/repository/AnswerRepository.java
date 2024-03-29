package com.potato.TutorCall.qna.repository;

import com.potato.TutorCall.qna.domain.Answer;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>, AnswerRepositoryCustom {

  @Modifying
  @Query("UPDATE Answer a set a.isDelete = :isDelete where a.id = :answerId")
  int deleteQuestion(
      @Param("answerId") Long answerId, @Param("isDelete") boolean isDelete);

  @Modifying
  @Query("UPDATE Answer a set a.isChosen = :isChosen where a.id = :answerId")
  int chooseAnswer(
      @Param("answerId") Long answerId, @Param("isChosen") boolean isChosen);
  @Modifying
  @Query("update Answer a set a.content=:content where a.id=:answerId")
  int updateAnswer(@Param("content")String content, @Param("answerId") Long answerId);
}
