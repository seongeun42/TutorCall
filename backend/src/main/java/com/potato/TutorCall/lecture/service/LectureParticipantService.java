package com.potato.TutorCall.lecture.service;

import com.potato.TutorCall.lecture.domain.Lecture;
import com.potato.TutorCall.lecture.repository.LectureParticipantRepository;
import com.potato.TutorCall.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LectureParticipantService {

    private final LectureParticipantRepository lpRepository;

    public Boolean existParticipant(Lecture lecture, User user) {
        return lpRepository.existsByLectureAndUser(lecture, user);
    }

}
