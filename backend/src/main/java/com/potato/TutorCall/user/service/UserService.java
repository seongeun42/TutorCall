package com.potato.TutorCall.user.service;

import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }


    @Transactional()
    public User save(User user){
        return this.userRepository.save(user);
    }
}