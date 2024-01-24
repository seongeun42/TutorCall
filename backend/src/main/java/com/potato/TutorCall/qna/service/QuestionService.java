package com.potato.TutorCall.qna.service;

import com.potato.TutorCall.exception.customException.InvalidException;
import com.potato.TutorCall.exception.customException.NotFoundException;
import com.potato.TutorCall.qna.domain.Question;
import com.potato.TutorCall.qna.dto.CommonResponseDto;
import com.potato.TutorCall.qna.dto.PaginationDto;
import com.potato.TutorCall.qna.dto.QuestionWriteDto;
import com.potato.TutorCall.qna.repository.QuestionRepository;
import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.tutor.repository.TagRepository;
import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {


    private final QuestionRepository questionRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;

    public QuestionService(QuestionRepository questionRepository, TagRepository tagRepository, UserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> question(int questionId){

        CommonResponseDto commonResponseDto;
        Question q= questionRepository.findById((long) questionId)
                .orElseThrow(()-> new NotFoundException("질문 조회 실패"));

        commonResponseDto = CommonResponseDto.builder().question(q).build();
        return ResponseEntity.ok(commonResponseDto);
    }

    public ResponseEntity<?> writeQuestion(QuestionWriteDto questionWriteDto){

        Tag tag = tagRepository.findById(questionWriteDto.getTagId())
                .orElseThrow(()-> new NotFoundException("질문 작성 실패"));
        User user = userRepository.findById(questionWriteDto.getWriterId())
                .orElseThrow(()-> new NotFoundException("질문 작성 실패"));
        CommonResponseDto commonResponseDto;
        Long questionId = null;

        questionId = questionRepository.writeQuestion(questionWriteDto, user, tag);

        if (questionId == null) throw new NotFoundException("질문 작성 실패");

        commonResponseDto = CommonResponseDto.builder().
                questionId(questionId).
                message("질문 게시글이 생성되었습니다.")
                .build();

        return ResponseEntity.ok(commonResponseDto);
    }

    public ResponseEntity<?> questionAll(Pageable pageable, PaginationDto paginationDto){

        Page<Question> list = null;
        CommonResponseDto commonResponseDto;
        list = questionRepository.findAllByContentContainsAndTag_IdAndIsEnd(pageable, paginationDto.getKeyword(), paginationDto.getTagId(), paginationDto.isEnd());

        commonResponseDto = CommonResponseDto.builder().questions(list).build();
        return ResponseEntity.ok(commonResponseDto);
    }

    public ResponseEntity<?> editQuestion(int questionId, QuestionWriteDto questionWriteDto){

        Long count = null;
        CommonResponseDto commonResponseDto;
        Tag tag = tagRepository.findById(questionWriteDto.getTagId())
                .orElseThrow(()-> new NotFoundException("질문 수정 실패"));
        User user = userRepository.findById(questionWriteDto.getWriterId())
                .orElseThrow(()-> new NotFoundException("질문 수정 실패"));

        Question editTarget = questionRepository.findById((long) questionId)
                .orElseThrow(()-> new NotFoundException("질문 수정 실패"));

        if(!editTarget.getId().equals(user.getId()))
            throw new InvalidException("수정 권한 없음");

        count = questionRepository.editQuestion(questionId, questionWriteDto, user, tag);
        if(count == 0)  throw new NotFoundException("질문 수정 실패");

        commonResponseDto = CommonResponseDto.builder().
                message("질문 게시글이 수정되었습니다.")
                .build();
        return ResponseEntity.ok(commonResponseDto);

    }

    public ResponseEntity<?> deleteQuestion(int questionId, long writerId){

        CommonResponseDto commonResponseDto;
        int count = questionRepository.deleteQuestionByIdAndWriter_Id((long) questionId, writerId);
        if (count ==0) throw new NotFoundException("질문 삭제 실패");

        commonResponseDto = CommonResponseDto.builder().
                message("질문 삭제 완료.")
                .build();
        return ResponseEntity.ok(commonResponseDto);
    }

}
