package com.potato.TutorCall.qna.repository;

import com.potato.TutorCall.qna.domain.Question;
import com.potato.TutorCall.qna.dto.QuestionWriteDto;
import com.potato.TutorCall.qna.dto.SearchFormDto;
import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionRepositoryCustom {

  Long writeQuestion(QuestionWriteDto questionWriteDto, User user, Tag tag);

  Page<Question> getList(Pageable pageable, SearchFormDto searchFormDto);

  long editQuestion(int questionId, QuestionWriteDto questionWriteDto, User user, Tag tag);
}
