package com.potato.TutorCall.lecture.service;

import com.potato.TutorCall.exception.customException.ForbiddenException;
import com.potato.TutorCall.exception.customException.NotFoundException;
import com.potato.TutorCall.lecture.domain.Lecture;
import com.potato.TutorCall.lecture.domain.LectureParticipant;
import com.potato.TutorCall.lecture.dto.LectureListResponseDto;
import com.potato.TutorCall.lecture.dto.LectureRequestDto;
import com.potato.TutorCall.lecture.dto.LectureSearchCondition;
import com.potato.TutorCall.lecture.repository.LectureParticipantRepository;
import com.potato.TutorCall.lecture.repository.LectureRepository;
import com.potato.TutorCall.lecture.repository.LectureSearchRepository;
import com.potato.TutorCall.tutor.service.TagService;
import com.potato.TutorCall.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final LectureSearchRepository lectureSearchRepository;
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
        if (dto.getTagId() != null && !dto.getTagId().equals(lecture.getTag().getId())) {
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

    public Page<LectureListResponseDto> getLectureList(LectureSearchCondition condition, Pageable pageable) {
        return lectureSearchRepository.search(condition, pageable);
    }

    public List<Lecture> findUserLectures(User user) {
        return lectureParticipantRepository.findLectureByUserOrderByIdDesc(user).stream().map(LectureParticipant::getLecture).toList();
    }
}
