package com.potato.TutorCall.notice.service;

import com.potato.TutorCall.notice.domain.Notice;
import com.potato.TutorCall.notice.repository.NoticeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;

}
