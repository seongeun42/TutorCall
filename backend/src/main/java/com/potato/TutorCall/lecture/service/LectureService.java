package com.potato.TutorCall.lecture.service;

import com.potato.TutorCall.exception.customException.ForbiddenException;
import com.potato.TutorCall.exception.customException.NotFoundException;
import com.potato.TutorCall.lecture.domain.Lecture;
import com.potato.TutorCall.lecture.dto.LectureRequestDto;
import com.potato.TutorCall.lecture.repository.LectureParticipantRepository;
import com.potato.TutorCall.lecture.repository.LectureRepository;
import com.potato.TutorCall.tutor.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LectureService {

    private final LectureRepository lectureRepository;
    private final LectureParticipantRepository lectureParticipantRepository;
    private final TagService tagService;

    @Transactional(readOnly = true)
    public Lecture findById(Long id) {
        Lecture lecture = lectureRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 과외입니다."));
        if (lecture.isDelete())
            throw new ForbiddenException("삭제된 과외입니다.");
        return lecture;
    }

    public Long save(Lecture lecture) {
        return lectureRepository.save(lecture).getId();
    }

    public void updateLecture(Long userId, Long lectureId, LectureRequestDto dto) {
        Lecture lecture = this.findById(lectureId);
        if (!lecture.getTutor().getId().equals(userId)) {
            throw new ForbiddenException("작성자가 아닙니다.");
        }
        lecture.changePromotionTitle(dto.getPromotionTitle());
        lecture.changePromotionContent(dto.getPromotionContent());
        lecture.changeMaxParticipants(dto.getMaxParticipant());
        lecture.changePromotionDue(dto.getPromotionDue());
        lecture.changePrice(dto.getPrice());
        if (dto.getTagId() != null && !dto.getTagId().equals(lecture.getTag().getId())){
            lecture.changeTag(tagService.findById(dto.getTagId()));
        }
    }

    public void deleteLecture(Long userId, Long lectureId) {
        Lecture lecture = this.findById(lectureId);
        if (!lecture.getTutor().getId().equals(userId)) {
            throw new ForbiddenException("작성자가 아닙니다.");
        }
        lecture.deleted();
        lectureParticipantRepository.deleteAllByLecture(lecture);
    }

}
