package com.potato.TutorCall.lecture.service;

import com.potato.TutorCall.exception.customException.ForbiddenException;
import com.potato.TutorCall.exception.customException.NotFoundException;
import com.potato.TutorCall.lecture.domain.Lecture;
import com.potato.TutorCall.lecture.dto.LectureListResponseDto;
import com.potato.TutorCall.lecture.dto.LectureRequestDto;
import com.potato.TutorCall.lecture.dto.LectureSearchCondition;
import com.potato.TutorCall.lecture.repository.LectureParticipantRepository;
import com.potato.TutorCall.lecture.repository.LectureRepository;
import com.potato.TutorCall.lecture.repository.LectureSearchRepository;
import com.potato.TutorCall.tutor.domain.Tutor;
import com.potato.TutorCall.tutor.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Transactional(readOnly = true)
    public Page<LectureListResponseDto> getLectureList(LectureSearchCondition condition, Pageable pageable) {
        return lectureSearchRepository.search(condition, pageable);
    }

    public void changePromotionState(Long lectureId, Tutor tutor, Boolean state) {
        Lecture lecture = this.findById(lectureId);
        if (!lecture.getTutor().equals(tutor))
            throw new ForbiddenException("상태 변경 권한이 없습니다.");
        lecture.changePromotionState(state);
    }

    public void changeLectureTerm(Long lectureId, Tutor tutor, LocalDateTime start, LocalDateTime end) {
        Lecture lecture = this.findById(lectureId);
        if (!lecture.getTutor().equals(tutor))
            throw new ForbiddenException("기간 변경 권한이 없습니다.");
        lecture.updateLectureStartAt(start);
        lecture.updateLectureEndAt(end);
    }

    public void changePromotionState() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        lectureRepository.changeLecturePromotionState(
                yesterday.atTime(0, 0, 0),
                yesterday.atTime(23, 59, 59)
        );
    }

    public void changeLectureState() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        lectureRepository.changeLectureState(
                yesterday.atTime(0, 0, 0),
                yesterday.atTime(23, 59, 59)
        );
    }

}
