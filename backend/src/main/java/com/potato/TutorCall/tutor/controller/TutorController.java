package com.potato.TutorCall.tutor.controller;

import com.potato.TutorCall.auth.SessionKey;
import com.potato.TutorCall.auth.dto.UserSessionDto;
import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.tutor.domain.Tutor;
import com.potato.TutorCall.tutor.dto.TagDto;
import com.potato.TutorCall.tutor.dto.TutorDetailDto;
import com.potato.TutorCall.tutor.service.TutorService;
import java.util.List;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/tutor")
public class TutorController {

  private final TutorService tutorService;

  @GetMapping("/{tutorId}")
  public ResponseEntity<?> getTutorInfo(@PathVariable("tutorId") Long tutorId) {
    Tutor tutor = tutorService.findById(tutorId);
    List<Tag> tags = tutorService.getTutorTags(tutor);
    log.info(tutorId + " 선생님을 조회했습니다.");
    return ResponseEntity.ok().body(new TutorDetailDto(tutor, tags));
  }

  @GetMapping("/tag")
  public ResponseEntity<?> getTutorTags(HttpSession session) {
    UserSessionDto userSessionDto = (UserSessionDto) session.getAttribute(SessionKey.USER);
    Long tutorId = userSessionDto.getId();
    Tutor tutor = tutorService.findById(tutorId);
    List<TagDto> tags = tutorService.getTutorTags(tutor).stream().map(TagDto::new).toList();
    log.info(tutorId + " 선생님의 태그 리스트를 조회했습니다.");
    return ResponseEntity.ok().body(tags);
  }

}
