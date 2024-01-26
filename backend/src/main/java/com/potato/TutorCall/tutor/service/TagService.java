package com.potato.TutorCall.tutor.service;

import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.tutor.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {

  private final TagRepository tagRepository;

  public Long save(Tag tag) {
    return tagRepository.save(tag).getId();
  }
}
