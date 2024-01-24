package com.potato.TutorCall.lecture.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLectureParticipant is a Querydsl query type for LectureParticipant
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLectureParticipant extends EntityPathBase<LectureParticipant> {

    private static final long serialVersionUID = -710191781L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLectureParticipant lectureParticipant = new QLectureParticipant("lectureParticipant");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QLecture lecture;

    public final com.potato.TutorCall.user.domain.QUser user;

    public QLectureParticipant(String variable) {
        this(LectureParticipant.class, forVariable(variable), INITS);
    }

    public QLectureParticipant(Path<? extends LectureParticipant> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLectureParticipant(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLectureParticipant(PathMetadata metadata, PathInits inits) {
        this(LectureParticipant.class, metadata, inits);
    }

    public QLectureParticipant(Class<? extends LectureParticipant> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lecture = inits.isInitialized("lecture") ? new QLecture(forProperty("lecture"), inits.get("lecture")) : null;
        this.user = inits.isInitialized("user") ? new com.potato.TutorCall.user.domain.QUser(forProperty("user")) : null;
    }

}

