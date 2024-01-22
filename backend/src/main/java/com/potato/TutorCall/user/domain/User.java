package com.potato.TutorCall.user.domain;

import com.potato.TutorCall.user.domain.enums.RoleType;
import com.potato.TutorCall.user.domain.enums.SnsType;
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
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String nickname;

    private String password;

    private RoleType role;

    private String profile;

    private SnsType sns;

    private int point;

    private LocalDateTime block;

    private boolean existNotification;

    private boolean unjoin;

    @CreatedDate
    private LocalDateTime joinDate;

}
