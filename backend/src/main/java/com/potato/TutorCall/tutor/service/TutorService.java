package com.potato.TutorCall.tutor.service;

import com.potato.TutorCall.exception.customException.NotFoundException;
import com.potato.TutorCall.tutor.domain.Tag;
import com.potato.TutorCall.tutor.domain.Tutor;
import com.potato.TutorCall.tutor.domain.TutorTag;
import com.potato.TutorCall.tutor.repository.TagRepository;
import com.potato.TutorCall.tutor.repository.TutorRepository;
import com.potato.TutorCall.tutor.repository.TutorTagRepository;
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
   * @param tutor Tutor
   * @param idList 새로운 Tag들의 id list
   */
  @Transactional
  public void changeTags(Tutor tutor, List<Long> idList) {
    // 태그가 기존 테이블에 존재하는지 확인
    List<Tag> newTags = tagRepository.findTagsByIdIn(idList);

    if (newTags.size() != idList.size()) {
      throw new RuntimeException("태그 정보가 잘못됐습니다.");
    }

    // 현재 선생의 태그 리스트
    List<TutorTag> currentTutorTags = tutorTagRepository.findByTutor(tutor);

    // 기존 리스트에 존재하는 태그들 중 새 태그 목록에 존재하지 않는 태그들은 제거
    currentTutorTags.removeIf(t -> !idList.contains(t.getTag().getId()));
    System.out.println(currentTutorTags.size());

    // 테이블에 새로 추가돼야 할 태그들 삽입
    for (Tag tag : newTags) {
      TutorTag newTutorTag = TutorTag.builder().tag(tag).tutor(tutor).build();
      currentTutorTags.add(newTutorTag);
    }
    System.out.println(currentTutorTags.size());

    //    tutorRepository.save(tutor);
    tutorTagRepository.saveAll(currentTutorTags);
  }
}
