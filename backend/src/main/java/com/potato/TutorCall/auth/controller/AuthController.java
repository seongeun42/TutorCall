package com.potato.TutorCall.auth.controller;


import com.potato.TutorCall.auth.CodeRepositorty;
import com.potato.TutorCall.auth.SessionKey;
import com.potato.TutorCall.auth.constant.SendEmailConfig;
import com.potato.TutorCall.auth.dto.SendEmailDto;
import com.potato.TutorCall.auth.dto.request.AuthLoginRequestDto;
import com.potato.TutorCall.auth.dto.request.EmailCheckResponseDto;
import com.potato.TutorCall.auth.dto.request.SendEmailRequestDto;
import com.potato.TutorCall.auth.service.AuthService;
import com.potato.TutorCall.auth.service.EmailService;
import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final EmailService emailService;
    private final AuthService authService;
    private final CodeRepositorty codeRepositorty;
    private final UserRepository userRepository;
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
    public ResponseEntity<?> sendEmail(SendEmailRequestDto sendEmailRequestDto) throws MessagingException, UnsupportedEncodingException {
        Map<String, String> response = new HashMap<>();

        String email = sendEmailRequestDto.getEmail();
        String code = this.emailService.codeGenerator(SendEmailConfig.CODE_LENGTH);

        SendEmailDto sendEmailDto = SendEmailDto
                .builder()
                .to(email)
//                .from("wocks6735@naver.com")
                .from(new InternetAddress(SendEmailConfig.FROM, SendEmailConfig.NAME))
                .title(SendEmailConfig.TITLE)
                .carbonCopy(SendEmailConfig.FROM)
                .subject("코드 번호 [" + code + "] 입니다.").build();

        this.emailService.sendEmail(sendEmailDto);
        //디비에 저장

        this.codeRepositorty.setCode(email, code);

        response.put("message", "이메일 인증 코드를 발송했습니다.");

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    @PostMapping("/email/check")
    public ResponseEntity<?> emailCheck(EmailCheckResponseDto emailCheckResponseDto) throws BadRequestException{
        // email , code
        boolean emailCheckSuccess = this.emailService.emailCheck(emailCheckResponseDto.getEmail(), emailCheckResponseDto.getCode());

        Map<String, String> response = new HashMap<>();
        if(!emailCheckSuccess) throw new BadRequestException("다시 확인해세요.");

        response.put("message", "인증 성공!");

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

}
