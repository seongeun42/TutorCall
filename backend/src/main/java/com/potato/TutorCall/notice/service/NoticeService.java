package com.potato.TutorCall.notice.service;

import com.potato.TutorCall.notice.domain.Faq;
import com.potato.TutorCall.notice.domain.Notice;
import com.potato.TutorCall.notice.dto.FaqDto;
import com.potato.TutorCall.notice.dto.NoticeDto;
import com.potato.TutorCall.notice.repository.FaqRepository;
import com.potato.TutorCall.notice.repository.NoticeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

@RequiredArgsConstructor
@Service
public class NoticeService {
  private final NoticeRepository noticeRepository;

  // 공지사항 게시글 작성 메서드
  public Notice save(NoticeDto noticeDto) {
    return noticeRepository.save(noticeDto.toEntity());
  }

  // 전체 공지사항 조회
  @Transactional(readOnly = true)
  public Page<Notice> getNoticeList(Pageable pageable) {
    return noticeRepository.findAllByOrderByIdDesc(pageable);
  }

  // 공지사항 상세글 조회
  public Notice findById(long id) {
    return noticeRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("존재하지 않는 게시글에 접근했습니다."));
  }

  // 공지사항 게시글 삭제
  public void delete(long id) {
    noticeRepository.deleteById(id);
  }

  // 공지사항 게시글 수정
  @Transactional
  public Notice update(long id, NoticeDto noticeDto) {
    Notice notice =
        noticeRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("수정 불가능한 게시물에 접근했습니다."));
    notice.changeTitle(noticeDto.getTitle());
    notice.changeContent(noticeDto.getContent());

    return notice;
  }

  private final FaqRepository faqRepository;

  // Faq 조회
  public List<Faq> faqFind() {
    return faqRepository.findAll();
  }

  // Faq 작성
  public Faq saveFaq(FaqDto faqDto) {
    return faqRepository.save(faqDto.toEntity());
  }

  // Faq 수정
  @Transactional
  public Faq updateFaq(long id, FaqDto faqDto) {
    Faq faq =
        faqRepository.findById(id).orElseThrow(() -> new NotFoundException("수정 불가능한 게시물에 접근했습니다."));
    faq.changeQuestion(faqDto.getQuestion());
    faq.changeAnswer(faqDto.getAnswer());

    return faq;
  }

  // Faq 삭제
  public void deleteFaq(long id) {
    faqRepository.deleteById(id);
  }
}
