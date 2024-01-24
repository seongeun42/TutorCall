package com.potato.TutorCall.auth.controller;


import com.potato.TutorCall.auth.SessionKey;
import com.potato.TutorCall.auth.dto.SendEmailDto;
import com.potato.TutorCall.auth.dto.request.AuthLoginRequestDto;
import com.potato.TutorCall.auth.dto.request.EmailCheckResponseDto;
import com.potato.TutorCall.auth.service.AuthService;
import com.potato.TutorCall.auth.service.EmailService;
import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final EmailService emailService;
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<?> login(AuthLoginRequestDto authLoginRequestDto, HttpServletRequest httpServletRequest) throws Exception{
        //유저 확인 메소드
        User user = this.authService.login(authLoginRequestDto);

        //세션 얻기
        //세션이 있으면 기존 세션 반환
        //세션이 없으면 생성 후 반환
        HttpSession session = httpServletRequest.getSession(true);

        //유저 세션를 세션에 넣는다.
        authService.saveUserInfoToSession(session, SessionKey.USER, user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/email")
    public ResponseEntity<?> sendEmail(@RequestBody String email) throws MessagingException {
        Map<String, String> response = new HashMap<>();

        String code = this.emailService.codeGenerator(5);
        SendEmailDto sendEmailDto = SendEmailDto
                .builder()
                .to(email)
                .from("wocks6735@google.com")
                .title("튜터콜 타이틀입니다.")
                .subject(code)
                .carbonCopy("????")
                .build();
        //메일 보내기
        this.emailService.sendEmail(sendEmailDto);

        //디비 저장
        this.u
        response.put("message", "이메일 인증 코드를 발송했습니다.");
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PostMapping("/email/check")
    public ResponseEntity<?> emailCheck(EmailCheckResponseDto emailCheckResponseDto){

        Map<String, String> response = new HashMap<>();
        response.put("message", "이메일 인증 코드를 발송했습니다.");
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session){
        this.authService.logout(session);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
