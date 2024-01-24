package com.potato.TutorCall.chat.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.potato.TutorCall.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ChatParticipant {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Chatroom chatroom;

    private LocalDateTime lastVisitAt;

    private LocalDateTime lastLeaveAt;




    // 생성자
    @Builder
    public ChatParticipant(User user, Chatroom chatroom) {
        this.user = user;
        this.chatroom = chatroom;
    }




    // 비즈니스 로직
    /**
     * 마지막으로 방문한 시간 갱신
     * @param lastVisitAt 방문한 시간
     */
    public void updateLastVisitAt(LocalDateTime lastVisitAt) {
        this.lastVisitAt = lastVisitAt;
    }

    /**
     * 마지막으로 떠난 시간 갱신
     * @param lastLeaveAt 떠난 시간
     */
    public void updateLastLeaveAt(LocalDateTime lastLeaveAt) {
        this.lastLeaveAt = lastLeaveAt;
    }

}
