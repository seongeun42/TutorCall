package com.potato.TutorCall.tutor.service;

import com.potato.TutorCall.tutor.domain.TutorTag;
import com.potato.TutorCall.tutor.repository.TutorTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TutorTagService {

    private final TutorTagRepository tutorTagRepository;

    public Long save(TutorTag tutorTag) {
        return tutorTagRepository.save(tutorTag).getId();
    }

}
