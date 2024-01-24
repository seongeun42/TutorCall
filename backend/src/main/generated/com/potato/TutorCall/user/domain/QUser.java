package com.potato.TutorCall.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1009401274L;

    public static final QUser user = new QUser("user");

    public final DateTimePath<java.time.LocalDateTime> block = createDateTime("block", java.time.LocalDateTime.class);

    public final ListPath<com.potato.TutorCall.chat.domain.ChatParticipant, com.potato.TutorCall.chat.domain.QChatParticipant> chatroomList = this.<com.potato.TutorCall.chat.domain.ChatParticipant, com.potato.TutorCall.chat.domain.QChatParticipant>createList("chatroomList", com.potato.TutorCall.chat.domain.ChatParticipant.class, com.potato.TutorCall.chat.domain.QChatParticipant.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final BooleanPath existNotification = createBoolean("existNotification");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.potato.TutorCall.inquiry.domain.Inquiry, com.potato.TutorCall.inquiry.domain.QInquiry> inquiryList = this.<com.potato.TutorCall.inquiry.domain.Inquiry, com.potato.TutorCall.inquiry.domain.QInquiry>createList("inquiryList", com.potato.TutorCall.inquiry.domain.Inquiry.class, com.potato.TutorCall.inquiry.domain.QInquiry.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> joinDate = createDateTime("joinDate", java.time.LocalDateTime.class);

    public final StringPath nickname = createString("nickname");

    public final ListPath<com.potato.TutorCall.notification.domain.Notification, com.potato.TutorCall.notification.domain.QNotification> notificationList = this.<com.potato.TutorCall.notification.domain.Notification, com.potato.TutorCall.notification.domain.QNotification>createList("notificationList", com.potato.TutorCall.notification.domain.Notification.class, com.potato.TutorCall.notification.domain.QNotification.class, PathInits.DIRECT2);

    public final StringPath password = createString("password");

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final ListPath<com.potato.TutorCall.payment.domain.PointHistory, com.potato.TutorCall.payment.domain.QPointHistory> pointHistoryList = this.<com.potato.TutorCall.payment.domain.PointHistory, com.potato.TutorCall.payment.domain.QPointHistory>createList("pointHistoryList", com.potato.TutorCall.payment.domain.PointHistory.class, com.potato.TutorCall.payment.domain.QPointHistory.class, PathInits.DIRECT2);

    public final StringPath profile = createString("profile");

    public final ListPath<com.potato.TutorCall.qna.domain.Question, com.potato.TutorCall.qna.domain.QQuestion> questionList = this.<com.potato.TutorCall.qna.domain.Question, com.potato.TutorCall.qna.domain.QQuestion>createList("questionList", com.potato.TutorCall.qna.domain.Question.class, com.potato.TutorCall.qna.domain.QQuestion.class, PathInits.DIRECT2);

    public final ListPath<com.potato.TutorCall.lecture.domain.LectureParticipant, com.potato.TutorCall.lecture.domain.QLectureParticipant> rectureList = this.<com.potato.TutorCall.lecture.domain.LectureParticipant, com.potato.TutorCall.lecture.domain.QLectureParticipant>createList("rectureList", com.potato.TutorCall.lecture.domain.LectureParticipant.class, com.potato.TutorCall.lecture.domain.QLectureParticipant.class, PathInits.DIRECT2);

    public final ListPath<com.potato.TutorCall.report.domain.Report, com.potato.TutorCall.report.domain.QReport> reportList = this.<com.potato.TutorCall.report.domain.Report, com.potato.TutorCall.report.domain.QReport>createList("reportList", com.potato.TutorCall.report.domain.Report.class, com.potato.TutorCall.report.domain.QReport.class, PathInits.DIRECT2);

    public final ListPath<com.potato.TutorCall.tutorcall.domain.RequestCall, com.potato.TutorCall.tutorcall.domain.QRequestCall> requestCallList = this.<com.potato.TutorCall.tutorcall.domain.RequestCall, com.potato.TutorCall.tutorcall.domain.QRequestCall>createList("requestCallList", com.potato.TutorCall.tutorcall.domain.RequestCall.class, com.potato.TutorCall.tutorcall.domain.QRequestCall.class, PathInits.DIRECT2);

    public final EnumPath<com.potato.TutorCall.user.domain.enums.RoleType> role = createEnum("role", com.potato.TutorCall.user.domain.enums.RoleType.class);

    public final EnumPath<com.potato.TutorCall.user.domain.enums.SnsType> sns = createEnum("sns", com.potato.TutorCall.user.domain.enums.SnsType.class);

    public final ListPath<com.potato.TutorCall.tutorcall.domain.TutorCall, com.potato.TutorCall.tutorcall.domain.QTutorCall> tutorCallList = this.<com.potato.TutorCall.tutorcall.domain.TutorCall, com.potato.TutorCall.tutorcall.domain.QTutorCall>createList("tutorCallList", com.potato.TutorCall.tutorcall.domain.TutorCall.class, com.potato.TutorCall.tutorcall.domain.QTutorCall.class, PathInits.DIRECT2);

    public final BooleanPath unjoin = createBoolean("unjoin");

    public final ListPath<com.potato.TutorCall.payment.domain.UserCoupon, com.potato.TutorCall.payment.domain.QUserCoupon> userCouponList = this.<com.potato.TutorCall.payment.domain.UserCoupon, com.potato.TutorCall.payment.domain.QUserCoupon>createList("userCouponList", com.potato.TutorCall.payment.domain.UserCoupon.class, com.potato.TutorCall.payment.domain.QUserCoupon.class, PathInits.DIRECT2);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

