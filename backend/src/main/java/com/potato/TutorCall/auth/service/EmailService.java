package com.potato.TutorCall.auth.service;

import com.potato.TutorCall.auth.CodeRepositorty;
import com.potato.TutorCall.auth.dto.SendEmailDto;
import com.potato.TutorCall.user.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {

  private final JavaMailSender emailSender;
  private final UserService userService;
  private final CodeRepositorty codeRepositorty;

  public void sendEmail(SendEmailDto sendEmailDto) throws MessagingException {
    MimeMessage message = emailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, false);

    // 제목, 내용 설정
    helper.setSubject(sendEmailDto.getSubject());
    helper.setText(sendEmailDto.getTitle(), false);

    // 참조자 설정
    helper.setCc(sendEmailDto.getCarbonCopy());

    // 발신자 설정
    helper.setFrom(sendEmailDto.getFrom());

    helper.setTo(sendEmailDto.getTo());

    emailSender.send(message);
  }

  public String codeGenerator(int num) {
    Random rand = new Random();
    return IntStream.range(0, num)
        .mapToObj(i -> Integer.toString(rand.nextInt()))
        .collect(Collectors.joining());
  }

  public boolean emailCheck(String email, String code) throws BadRequestException {
    // User user = this.userService.findUserByEmail(email);
    String findCode = this.codeRepositorty.getCode(email); // 이메일로 받아옴
    if (findCode == null) throw new BadRequestException("다시 시도해주세요.");
    if (!code.equals(findCode)) throw new BadRequestException("다시 시도해주세요.");
    codeRepositorty.deleteCode(email);
    return true;
  }
}
