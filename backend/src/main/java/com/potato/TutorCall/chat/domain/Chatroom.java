package com.potato.TutorCall.chat.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.potato.TutorCall.chat.domain.enums.ChatroomType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Chatroom {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private ChatroomType type;

    @CreatedDate
    private LocalDateTime createdAt;




    // 양방향 연관 관계
    @JsonManagedReference
    @OneToMany(mappedBy = "chatroom", fetch = FetchType.LAZY)
    private List<ChatParticipant> participantList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "chatroom", fetch = FetchType.LAZY)
    private List<ChatMessage> messageList = new ArrayList<>();

}
