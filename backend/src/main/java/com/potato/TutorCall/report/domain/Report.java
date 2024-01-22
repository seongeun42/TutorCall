package com.potato.TutorCall.report.domain;

import com.potato.TutorCall.report.domain.enums.ReportType;
import com.potato.TutorCall.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
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

    private User reporter;

    private Long reported;

    private ReportType type;

    private String content;

    private boolean proceedState;

    private String result;

    @CreatedDate
    private LocalDateTime createdAt;

}
