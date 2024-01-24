package com.potato.TutorCall.review.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = 1871427296L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReview review = new QReview("review");

    public final NumberPath<Integer> communicationRate = createNumber("communicationRate", Integer.class);

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.potato.TutorCall.lecture.domain.QLecture lecture;

    public final NumberPath<Integer> mannerRate = createNumber("mannerRate", Integer.class);

    public final NumberPath<Integer> professionalismRate = createNumber("professionalismRate", Integer.class);

    public final com.potato.TutorCall.user.domain.QUser reviewer;

    public final com.potato.TutorCall.tutor.domain.QTutor tutor;

    public QReview(String variable) {
        this(Review.class, forVariable(variable), INITS);
    }

    public QReview(Path<? extends Review> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReview(PathMetadata metadata, PathInits inits) {
        this(Review.class, metadata, inits);
    }

    public QReview(Class<? extends Review> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lecture = inits.isInitialized("lecture") ? new com.potato.TutorCall.lecture.domain.QLecture(forProperty("lecture"), inits.get("lecture")) : null;
        this.reviewer = inits.isInitialized("reviewer") ? new com.potato.TutorCall.user.domain.QUser(forProperty("reviewer")) : null;
        this.tutor = inits.isInitialized("tutor") ? new com.potato.TutorCall.tutor.domain.QTutor(forProperty("tutor"), inits.get("tutor")) : null;
    }

}

