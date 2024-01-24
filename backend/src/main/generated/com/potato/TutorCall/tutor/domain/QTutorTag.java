package com.potato.TutorCall.tutor.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTutorTag is a Querydsl query type for TutorTag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTutorTag extends EntityPathBase<TutorTag> {

    private static final long serialVersionUID = 1718155074L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTutorTag tutorTag = new QTutorTag("tutorTag");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QTag tag;

    public final QTutor tutor;

    public QTutorTag(String variable) {
        this(TutorTag.class, forVariable(variable), INITS);
    }

    public QTutorTag(Path<? extends TutorTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTutorTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTutorTag(PathMetadata metadata, PathInits inits) {
        this(TutorTag.class, metadata, inits);
    }

    public QTutorTag(Class<? extends TutorTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.tag = inits.isInitialized("tag") ? new QTag(forProperty("tag")) : null;
        this.tutor = inits.isInitialized("tutor") ? new QTutor(forProperty("tutor"), inits.get("tutor")) : null;
    }

}

