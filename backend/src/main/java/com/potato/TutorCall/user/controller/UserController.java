package com.potato.TutorCall.user.controller;

import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.dto.UserDto;
import com.potato.TutorCall.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  @GetMapping("/{userId}")
  public ResponseEntity<?> getUserInfo(@PathVariable("userId") Long userId) {
    User user = this.userService.findById(userId);
    UserDto userDto =
        UserDto.builder()
            .point(user.getPoint())
            .userId(user.getId())
            .nickname(user.getNickname())
            .profile(user.getProfile())
            .build();
    return new ResponseEntity<>(userDto, HttpStatus.OK);
  }
}
