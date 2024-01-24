package com.potato.TutorCall.tutorcall.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRequestCall is a Querydsl query type for RequestCall
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRequestCall extends EntityPathBase<RequestCall> {

    private static final long serialVersionUID = -2063285263L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRequestCall requestCall = new QRequestCall("requestCall");

    public final com.potato.TutorCall.user.domain.QUser caller;

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<ResponseCall, QResponseCall> responseCallList = this.<ResponseCall, QResponseCall>createList("responseCallList", ResponseCall.class, QResponseCall.class, PathInits.DIRECT2);

    public final com.potato.TutorCall.tutor.domain.QTag tag;

    public final StringPath title = createString("title");

    public final NumberPath<Integer> tutorCount = createNumber("tutorCount", Integer.class);

    public QRequestCall(String variable) {
        this(RequestCall.class, forVariable(variable), INITS);
    }

    public QRequestCall(Path<? extends RequestCall> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRequestCall(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRequestCall(PathMetadata metadata, PathInits inits) {
        this(RequestCall.class, metadata, inits);
    }

    public QRequestCall(Class<? extends RequestCall> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.caller = inits.isInitialized("caller") ? new com.potato.TutorCall.user.domain.QUser(forProperty("caller")) : null;
        this.tag = inits.isInitialized("tag") ? new com.potato.TutorCall.tutor.domain.QTag(forProperty("tag")) : null;
    }

}

