package com.potato.TutorCall.user.service;

import com.potato.TutorCall.auth.dto.request.SignupRequestDto;
import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.domain.enums.RoleType;
import com.potato.TutorCall.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }


    @Transactional()
    public User save(User user){
        return this.userRepository.save(user);
    }

    public User findByNickname(String nickname){ return this.userRepository.findByNickname(nickname); }

    public User signup(SignupRequestDto signupRequestDto) throws DuplicateKeyException{
        User user = this.userRepository.findByEmail(signupRequestDto.getEmail());
        if(user != null) throw new DuplicateKeyException("이미 존재하는 이메일입니다.");
        // user nickname도 중복체크???
        if(user.getNickname().equals(signupRequestDto.getNickname())) throw new DuplicateKeyException("이미 존재하는 닉네임입니다..");
        user = User.builder()
                .email(signupRequestDto.getEmail())
                .nickname(signupRequestDto.getNickname())
                .password(passwordEncoder.encode(signupRequestDto.getPassword()))
                .role(RoleType.USER)
                .point(0)
                .build();
        this.userRepository.save(user);

        return user;
    }
}