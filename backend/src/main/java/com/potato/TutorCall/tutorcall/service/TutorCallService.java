package com.potato.TutorCall.tutorcall.service;

import com.potato.TutorCall.exception.customException.NotFoundException;
import com.potato.TutorCall.tutorcall.domain.TutorCall;
import com.potato.TutorCall.tutorcall.repository.TutorCallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TutorCallService {

    private final TutorCallRepository tutorcallRepository;

    @Transactional(readOnly = true)
    public TutorCall findById(Long id) {
        return tutorcallRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 튜터콜입니다."));
    }

}
