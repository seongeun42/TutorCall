package com.potato.TutorCall.tutorcall.domain;

import com.potato.TutorCall.tutor.domain.Tutor;
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
public class TutorCall {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Tutor tutor;

    private User user;

    private String problemContent;

    private String replayVideo;

    private String liveUrl;

    private boolean liveState;

    private int price;

    @CreatedDate
    private LocalDateTime createdAt;

}
