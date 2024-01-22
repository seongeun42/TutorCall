package com.potato.TutorCall.tutorcall.domain;

import com.potato.TutorCall.tutor.domain.Tag;
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
public class RequestCall {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private User caller;

    private String title;

    private String content;

    private Tag tag;

    private int tutorCount;

    @CreatedDate
    private LocalDateTime createdAt;

}
