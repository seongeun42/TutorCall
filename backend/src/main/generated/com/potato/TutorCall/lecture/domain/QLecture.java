package com.potato.TutorCall.lecture.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLecture is a Querydsl query type for Lecture
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLecture extends EntityPathBase<Lecture> {

    private static final long serialVersionUID = -1218158920L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLecture lecture = new QLecture("lecture");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDelete = createBoolean("isDelete");

    public final DateTimePath<java.time.LocalDateTime> lectureEndAt = createDateTime("lectureEndAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> lectureStartAt = createDateTime("lectureStartAt", java.time.LocalDateTime.class);

    public final BooleanPath lectureState = createBoolean("lectureState");

    public final StringPath liveUrl = createString("liveUrl");

    public final NumberPath<Integer> maxParticipants = createNumber("maxParticipants", Integer.class);

    public final ListPath<LectureParticipant, QLectureParticipant> participantList = this.<LectureParticipant, QLectureParticipant>createList("participantList", LectureParticipant.class, QLectureParticipant.class, PathInits.DIRECT2);

    public final NumberPath<Integer> participants = createNumber("participants", Integer.class);

    public final StringPath password = createString("password");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath promotionContent = createString("promotionContent");

    public final DateTimePath<java.time.LocalDateTime> promotionCreatedAt = createDateTime("promotionCreatedAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> promotionDue = createDateTime("promotionDue", java.time.LocalDateTime.class);

    public final BooleanPath promotionState = createBoolean("promotionState");

    public final StringPath promotionTitle = createString("promotionTitle");

    public final ListPath<com.potato.TutorCall.review.domain.Review, com.potato.TutorCall.review.domain.QReview> reviewList = this.<com.potato.TutorCall.review.domain.Review, com.potato.TutorCall.review.domain.QReview>createList("reviewList", com.potato.TutorCall.review.domain.Review.class, com.potato.TutorCall.review.domain.QReview.class, PathInits.DIRECT2);

    public final com.potato.TutorCall.tutor.domain.QTag tag;

    public final com.potato.TutorCall.tutor.domain.QTutor tutor;

    public QLecture(String variable) {
        this(Lecture.class, forVariable(variable), INITS);
    }

    public QLecture(Path<? extends Lecture> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLecture(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLecture(PathMetadata metadata, PathInits inits) {
        this(Lecture.class, metadata, inits);
    }

    public QLecture(Class<? extends Lecture> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.tag = inits.isInitialized("tag") ? new com.potato.TutorCall.tutor.domain.QTag(forProperty("tag")) : null;
        this.tutor = inits.isInitialized("tutor") ? new com.potato.TutorCall.tutor.domain.QTutor(forProperty("tutor"), inits.get("tutor")) : null;
    }

}

