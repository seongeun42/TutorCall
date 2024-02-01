package com.potato.TutorCall.tutor.service;

import com.potato.TutorCall.exception.customException.NotFoundException;
import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.tutor.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TagService {

  private final TagRepository tagRepository;

  public Long save(Tag tag) {
    return tagRepository.save(tag).getId();
  }

  @Transactional(readOnly = true)
  public Tag findById(Long id) {
    return tagRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("존재하지 않는 태그입니다."));
  }

}
