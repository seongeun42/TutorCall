package com.potato.TutorCall.notice.service;

import com.potato.TutorCall.notice.domain.Notice;
import com.potato.TutorCall.notice.dto.NoticeDto;
import com.potato.TutorCall.notice.repository.NoticeRepository;
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

    public List<Notice> findAll() {
        return noticeRepository.findAll();
    }
}
