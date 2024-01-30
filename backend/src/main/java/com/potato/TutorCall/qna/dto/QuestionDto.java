package com.potato.TutorCall.qna.dto;


import com.potato.TutorCall.qna.domain.Question;
import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.tutor.dto.TagDto;
import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.dto.UserSimpleDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QuestionDto {
    private UserSimpleDto writer;
    private String title;
    private String content;
    private TagDto tag;
    private boolean isEnd;
    private boolean isDelete;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public QuestionDto(Question question){
        this.writer = new UserSimpleDto(question.getWriter());
        this.title = question.getTitle();
        this.content = question.getContent();
        this.tag = new TagDto(question.getTag());
        this.isEnd = question.isEnd();
        this.isDelete = question.isDelete();
        this.createdAt = question.getCreatedAt();
        this.modifiedAt = question.getModifiedAt();
    }

}
