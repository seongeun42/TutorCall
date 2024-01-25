package com.potato.TutorCall.qna.repository;

import com.potato.TutorCall.qna.dto.QuestionWriteDto;
import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.user.domain.User;

public interface QuestionRepositoryCustom {

  Long writeQuestion(QuestionWriteDto questionWriteDto, User user, Tag tag);

  long editQuestion(int questionId, QuestionWriteDto questionWriteDto, User user, Tag tag);
}
