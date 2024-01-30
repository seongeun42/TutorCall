package com.potato.TutorCall.tutor.service;

import com.potato.TutorCall.exception.customException.NotFoundException;
import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.tutor.domain.Tutor;
import com.potato.TutorCall.tutor.domain.TutorTag;
import com.potato.TutorCall.tutor.repository.TagRepository;
import com.potato.TutorCall.tutor.repository.TutorRepository;
import com.potato.TutorCall.tutor.repository.TutorTagRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TutorService {

  private final TutorRepository tutorRepository;
  private final TutorTagRepository tutorTagRepository;
  private final TagRepository tagRepository;

  /**
   * Tutor를 DB에 저장하는 함수
   *
   * @param tutor 저장할 Tutor
   * @return Tutor id
   */
  public Long save(Tutor tutor) {
    return tutorRepository.save(tutor).getId();
  }

  /**
   * 하나의 Tutor를 조회하는 함수
   *
   * @param id 조회할 Tutor id
   * @return Tutor
   */
  @Transactional(readOnly = true)
  public Tutor findById(Long id) {
    return tutorRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("해당 선생님이 존재하지 않습니다."));
  }

  /**
   * Tutor 소개 문구를 수정하는 함수
   *
   * @param id Tutor id
   * @param newIntro
   */
  public void changeIntroduction(Long id, String newIntro) {
    Tutor tutor = this.findById(id);
    tutor.changeIntroduction(newIntro);
  }

  /**
   * Tutor의 활성화 상태를 토글하는 함수
   *
   * @param id Tutor id
   */
  public void changeActiveState(Long id) {
    Tutor tutor = this.findById(id);
    tutor.changeActiveState(!tutor.isActive());
  }

  /**
   * Tutor의 Tag list를 조회하는 함수
   *
   * @param tutor Tutor
   * @return Tutor의 Tag list
   */
  @Transactional(readOnly = true)
  public List<Tag> getTutorTags(Tutor tutor) {
    return tutorTagRepository.getTagsByTutor(tutor);
  }

  /**
   * Tutor의 Tag list를 업데이트
   *
   * @param id id
   * @param idList 새로운 Tag들의 id list
   */
  @Transactional
  public void changeTags(Long id, List<Long> idList) {
    Tutor tutor = tutorRepository.findById(id).get();
    // 새로 설정될 태그들
    List<Tag> newTags = tagRepository.findTagsByIdIn(idList);

    // 태그가 기존 테이블에 존재하는지 확인
    if (newTags.size() != idList.size()) {
      throw new RuntimeException("태그 정보가 잘못됐습니다.");
    }

    // 현재 선생의 태그 리스트
    List<TutorTag> currentTutorTags = tutor.getTutorTagList();
    // 삭제할 태그들 목록
    List<TutorTag> deleteTutorTags = new ArrayList<>();

    for (TutorTag tutorTag : currentTutorTags) {
      if (newTags.contains(tutorTag.getTag())) {
        newTags.remove(tutorTag.getTag());
      } else {
        deleteTutorTags.add(tutorTag);
      }
    }
    currentTutorTags.retainAll(deleteTutorTags);

    for (Tag tag : newTags) {
      TutorTag tutorTag = TutorTag.builder().tutor(tutor).tag(tag).build();

      currentTutorTags.add(tutorTag);
    }

    tutorTagRepository.saveAll(currentTutorTags);
    tutorTagRepository.deleteAllInBatch(deleteTutorTags);
  }
}
