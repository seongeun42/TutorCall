package com.potato.TutorCall.lecture.service;

import com.potato.TutorCall.exception.customException.DuplicatedException;
import com.potato.TutorCall.exception.customException.NotFoundException;
import com.potato.TutorCall.exception.customException.OutOfRangeException;
import com.potato.TutorCall.lecture.domain.Lecture;
import com.potato.TutorCall.lecture.domain.LectureParticipant;
import com.potato.TutorCall.lecture.repository.LectureParticipantRepository;
import com.potato.TutorCall.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LectureParticipantService {

    private final LectureParticipantRepository lpRepository;

    /**
     * 과외 신청 함수
     * @param lecture
     * @param user
     * @return 신청된 ID
     */
    public Long registLecture(Lecture lecture, User user) {
        if (this.existParticipant(lecture.getId(), user.getId()))
            throw new DuplicatedException("이미 등록한 과외입니다.");
        if (lecture.getParticipants() >= lecture.getMaxParticipants())
            throw new OutOfRangeException("이미 최대 인원입니다.");
        lecture.changeParticipants(1);
        return lpRepository.save(LectureParticipant.builder().lecture(lecture).user(user).build()).getId();
    }

    /**
     * 과외 신청 취소 함수
     * @param lecture
     * @param userId
     */
    public void unRegistLecture(Lecture lecture, Long userId) {
        if (!this.existParticipant(lecture.getId(), userId))
            throw new NotFoundException("존재하지 않는 등록 내역입니다.");
        if (lecture.getParticipants() <= 0)
            throw new OutOfRangeException("이미 최소 인원입니다.");
        lecture.changeParticipants(-1);
        lpRepository.deleteByLectureAndUser(lecture.getId(), userId);
    }

    @Transactional(readOnly = true)
    public LectureParticipant findById(Long id) {
        return lpRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 등록 내역입니다."));
    }

    @Transactional(readOnly = true)
    public Boolean existParticipant(Long lectureId, Long userId) {
        return lpRepository.existByLectureAndUser(lectureId, userId);
    }

}
