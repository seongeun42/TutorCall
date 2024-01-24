package com.potato.TutorCall.qna.repository;

import com.potato.TutorCall.qna.domain.Question;
import com.potato.TutorCall.qna.dto.PaginationDto;
import com.potato.TutorCall.qna.dto.QuestionWriteDto;
import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionRepositoryCustom {


    //2. 모든 질문 조회(pageable)
    //Page<Question> findAll(PaginationDto pagenationDto, Pageable pageable);
    //3. 새 질문 작성
    Long writeQuestion(QuestionWriteDto questionWriteDto, User user, Tag tag);
    //4. 질문글 수정 (id, dto)
    long editQuestion(int questionId, QuestionWriteDto questionWriteDto, User user, Tag tag);

}
