package com.potato.TutorCall.qna.repository;

import com.potato.TutorCall.qna.domain.Question;
import com.potato.TutorCall.qna.dto.PaginationDto;
import com.potato.TutorCall.qna.dto.QuestionWriteDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionRepositoryCustom {

    //1. 특정 질문 조회 (id)
    Question findByQuestionById(int questionId);
    //2. 모든 질문 조회(pageable)
    Page<Question> findAll(PaginationDto pagenationDto, Pageable pageable);
    //3. 새 질문 작성
    void writeQuestion(QuestionWriteDto questionWriteDto);
    //4. 질문글 수정 (id, dto)
     void editQuestion(int questionId, QuestionWriteDto questionWriteDto);
    //5. 질문글 삭제 (id)
    void deleteQuestionById(int questionId);

}
