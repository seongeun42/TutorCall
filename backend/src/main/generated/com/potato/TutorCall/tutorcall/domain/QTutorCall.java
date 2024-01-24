package com.potato.TutorCall.tutorcall.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTutorCall is a Querydsl query type for TutorCall
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTutorCall extends EntityPathBase<TutorCall> {

    private static final long serialVersionUID = -1230764520L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTutorCall tutorCall = new QTutorCall("tutorCall");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath liveState = createBoolean("liveState");

    public final StringPath liveUrl = createString("liveUrl");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath problemContent = createString("problemContent");

    public final StringPath replayVideo = createString("replayVideo");

    public final com.potato.TutorCall.review.domain.QReview review;

    public final com.potato.TutorCall.tutor.domain.QTag tag;

    public final com.potato.TutorCall.tutor.domain.QTutor tutor;

    public final com.potato.TutorCall.user.domain.QUser user;

    public QTutorCall(String variable) {
        this(TutorCall.class, forVariable(variable), INITS);
    }

    public QTutorCall(Path<? extends TutorCall> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTutorCall(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTutorCall(PathMetadata metadata, PathInits inits) {
        this(TutorCall.class, metadata, inits);
    }

    public QTutorCall(Class<? extends TutorCall> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.review = inits.isInitialized("review") ? new com.potato.TutorCall.review.domain.QReview(forProperty("review"), inits.get("review")) : null;
        this.tag = inits.isInitialized("tag") ? new com.potato.TutorCall.tutor.domain.QTag(forProperty("tag")) : null;
        this.tutor = inits.isInitialized("tutor") ? new com.potato.TutorCall.tutor.domain.QTutor(forProperty("tutor"), inits.get("tutor")) : null;
        this.user = inits.isInitialized("user") ? new com.potato.TutorCall.user.domain.QUser(forProperty("user")) : null;
    }

}

