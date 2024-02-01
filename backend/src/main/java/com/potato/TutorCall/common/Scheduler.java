package com.potato.TutorCall.common;

import com.potato.TutorCall.lecture.service.LectureService;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Scheduler {

    private final LectureService lectureService;

    /**
     * 매일 자정 전날이 마감기한인 과외 모집글 상태 변경
     */
    @Scheduled(cron = "0 0 0 * * *")
    public void changePromotionState() {
        lectureService.changePromotionState();
        log.info("마감 기한이 지난 과외의 모집 상태를 수정했습니다.");
    }

    /**
     * 매일 자정 전날이 종료일인 과외 상태 변경
     */
    @Scheduled(cron = "0 0 0 * * *")
    public void changeLectureState() {
        lectureService.changeLectureState();
        log.info("종료일이 지난 과외 상태를 수정했습니다.");
    }

}
