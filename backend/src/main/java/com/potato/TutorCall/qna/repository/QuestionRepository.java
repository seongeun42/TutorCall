package com.potato.TutorCall.qna.repository;

import com.potato.TutorCall.qna.domain.Question;
import java.util.Optional;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository
    extends JpaRepository<Question, Long>, QuestionRepositoryCustom {


    @Modifying
    @Query("UPDATE Question q set q.isDelete = :isDelete where q.id = :questionId and q.writer.id = :writerId")
    int updateQuestionByIdAndWriter_IdAndIsDelete(@Param("questionId") Long questionId,  @Param("isDelete") boolean isDelete, @Param("writerId") long writerId);

    @Modifying
    @Query("UPDATE Question q set q.isDelete = :isDelete where q.id = :questionId")
    int updateQuestionByIdAndIsDelete(@Param("questionId") Long questionId,  @Param("isDelete") boolean isDelete);
    Page<Question> findAllByContentContainsAndTag_IdAndIsEndAndIsDelete(Pageable pageable, String keyword, Long tagId, boolean isEnd, boolean isDelete);
    Optional<Object> findQuestionByIdAndIsDelete(long questionId, boolean isDelete);


}
