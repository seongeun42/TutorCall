package com.potato.TutorCall.auth.service;

import com.potato.TutorCall.auth.dto.SendEmailDto;
import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;
    private final UserService userService;

    public void sendEmail(SendEmailDto sendEmailDto) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        //제목, 내용 설정
        helper.setSubject(sendEmailDto.getSubject());
        helper.setText(sendEmailDto.getTitle(), false);

        // 참조자 설정
        helper.setCc(sendEmailDto.getCarbonCopy());

        //발신자 설정
        helper.setFrom(sendEmailDto.getFrom());

        helper.setTo(sendEmailDto.getTo());

        emailSender.send(message);
    }
    public String codeGenerator(int num){
        Random rand = new Random();
        return IntStream.range(0, num)
                .mapToObj(i -> Integer.toString(rand.nextInt())).collect(Collectors.joining());
    }

    public boolean emailCheck(String email, String code){
        User user = this.userService.findUserByEmail(email);
        // TODO: 코드 체크.. 어디서 해야 할 지 모르겠네;
        return true;
    }
}
