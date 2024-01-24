package com.potato.TutorCall.mypage.service;

import com.potato.TutorCall.exception.customException.NotFoundException;
import com.potato.TutorCall.mypage.dto.res.MyPageProfileResDto;
import com.potato.TutorCall.tutor.domain.Tutor;
import com.potato.TutorCall.tutor.domain.TutorTag;
import com.potato.TutorCall.tutor.repository.TutorRepository;
import com.potato.TutorCall.tutor.repository.TutorTagRepository;
import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MypageService {

    private final UserRepository userRepository;
    private final TutorRepository tutorRepository;
    private final TutorTagRepository tutorTagRepository;

    public MyPageProfileResDto getUserProfile(Long id) {
        User currentUser = userRepository.findById(id).orElseThrow(() -> new NotFoundException("사용자 정보가 없습니다."));

        MyPageProfileResDto userInfo = new MyPageProfileResDto(currentUser);

        // 유저가 선생이라면
        Optional<Tutor> currentTutor = tutorRepository.findByUser(currentUser);
        if(currentTutor.isPresent()) {
            List<TutorTag> tutorTagList = tutorTagRepository.findByTutor(currentTutor.get());

            userInfo.addTutorInfo(currentTutor.get(), tutorTagList);
        }

        return userInfo;
    }
}
