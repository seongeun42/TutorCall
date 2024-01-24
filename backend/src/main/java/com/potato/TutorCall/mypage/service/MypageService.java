package com.potato.TutorCall.mypage.service;

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

    public Optional<MyPageProfileResDto> getUserProfile(Long id) {
        Optional<User> currentUser = userRepository.findById(id);

        // 들어온 id에 해당하는 유저 정보가 없으면
        if(currentUser.isEmpty()) {
            return Optional.empty();
        }

        MyPageProfileResDto userInfo = new MyPageProfileResDto(currentUser.get());

        // 유저가 선생이라면
        Optional<Tutor> currentTutor = tutorRepository.findByUser(currentUser.get());
        if(currentTutor.isPresent()) {
            List<TutorTag> tutorTagList = tutorTagRepository.findByTutor(currentTutor.get());

            userInfo.addTutorInfo(currentTutor.get(), tutorTagList);
        }

        return Optional.of(userInfo);
    }
}
