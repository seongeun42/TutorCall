package com.potato.TutorCall.report.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.potato.TutorCall.report.domain.enums.ReportType;
import com.potato.TutorCall.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Report {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private User reporter;

    private Long reported;

    private ReportType type;

    private String content;

    private boolean proceedState;

    private String result;

    @CreatedDate
    private LocalDateTime createdAt;




    // 생성자
    @Builder
    public Report(User reporter, Long reported, ReportType type, String content, String result) {
        this.reporter = reporter;
        this.reported = reported;
        this.type = type;
        this.content = content;
        this.result = result;
    }


    // 비즈니스 로직
    /**
     * 진행 상황 끝으로 변경
     */
    public void processDone() {
        this.proceedState = true;
    }

    /**
     * 처리 결과 반영
     *
     * @param result 처리 결과
     */
    public void setResult(String result) {
        this.result = result;
    }

}
