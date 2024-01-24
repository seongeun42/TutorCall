package com.potato.TutorCall.tutor.service;

import com.potato.TutorCall.exception.customException.NotFoundException;
import com.potato.TutorCall.tutor.domain.Tutor;
import com.potato.TutorCall.tutor.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TutorService {

    private final TutorRepository tutorRepository;

    /**
     * Tutor를 DB에 저장하는 함수
     * @param tutor 저장할 Tutor
     * @return Tutor id
     */
    public Long save(Tutor tutor) {
        return tutorRepository.save(tutor).getId();
    }

    /**
     * 하나의 Tutor를 조회하는 함수
     * @param id 조회할 Tutor id
     * @return Tutor
     */
    @Transactional(readOnly = true)
    public Tutor findById(Long id) {
        return tutorRepository.findById(id).orElseThrow(() -> new NotFoundException("해당 선생님이 존재하지 않습니다."));
    }

    /**
     *  Tutor 소개 문구를 수정하는 함수
     * @param id Tutor id
     * @param newIntro
     */
    public void changeIntroduction(Long id, String newIntro) {
        Tutor tutor = this.findById(id);
        tutor.changeIntroduction(newIntro);
    }

    /**
     * Tutor의 활성화 상태를 토글하는 함수
     * @param id Tutor id
     */
    public void changeActiveState(Long id) {
        Tutor tutor = this.findById(id);
        tutor.changeActiveState(!tutor.isActive());
    }

}
