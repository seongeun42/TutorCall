package com.potato.TutorCall.user.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.potato.TutorCall.chat.domain.ChatParticipant;
import com.potato.TutorCall.inquiry.domain.Inquiry;
import com.potato.TutorCall.lecture.domain.LectureParticipant;
import com.potato.TutorCall.notification.domain.Notification;
import com.potato.TutorCall.payment.domain.PointHistory;
import com.potato.TutorCall.payment.domain.UserCoupon;
import com.potato.TutorCall.qna.domain.Question;
import com.potato.TutorCall.report.domain.Report;
import com.potato.TutorCall.tutorcall.domain.RequestCall;
import com.potato.TutorCall.tutorcall.domain.TutorCall;
import com.potato.TutorCall.user.domain.enums.RoleType;
import com.potato.TutorCall.user.domain.enums.SnsType;
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




    // 양방향 연관 관계
    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Inquiry> inquiryList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "reporter", fetch = FetchType.LAZY)
    private List<Report> reportList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<PointHistory> pointHistoryList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<UserCoupon> userCouponList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY)
    private List<Notification> notificationList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<LectureParticipant> rectureList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY)
    private List<Question> questionList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<TutorCall> tutorCallList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ChatParticipant> chatroomList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "caller", fetch = FetchType.LAZY)
    private List<RequestCall> requestCallList = new ArrayList<>();




}
