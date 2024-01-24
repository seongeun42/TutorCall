package com.potato.TutorCall.notice.service;

import com.potato.TutorCall.notice.domain.Faq;
import com.potato.TutorCall.notice.domain.Notice;
import com.potato.TutorCall.notice.dto.NoticeDto;
import com.potato.TutorCall.notice.dto.UpdateNotice;
import com.potato.TutorCall.notice.repository.FaqRepository;
import com.potato.TutorCall.notice.repository.NoticeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    // 공지사항 게시글 작성 메서드
    public Notice save(NoticeDto noticeDto) {
        return noticeRepository.save(noticeDto.toEntity());
    }

    // 전체 공지사항 조회
    public List<Notice> findAll() {
        return noticeRepository.findAll();
    }

    // 공지사항 상세글 조회
    public Notice findById(long id) {
        return noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    // 공지사항 게시글 삭제
    public void delete(long id) {
        noticeRepository.deleteById(id);
    }

    // 공지사항 게시글 수정
    @Transactional
    public Notice update(long id, UpdateNotice updateNotice) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
        notice.changeTitle(updateNotice.getTitle());
        notice.changeContent(updateNotice.getContent());

        return notice;
    }

    private final FaqRepository faqRepository;
    public List<Faq> faqFind() {
        return faqRepository.findAll();
    }


}
