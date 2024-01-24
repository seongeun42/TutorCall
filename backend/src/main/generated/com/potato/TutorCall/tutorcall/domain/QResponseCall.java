package com.potato.TutorCall.tutorcall.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QResponseCall is a Querydsl query type for ResponseCall
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResponseCall extends EntityPathBase<ResponseCall> {

    private static final long serialVersionUID = 624526427L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QResponseCall responseCall = new QResponseCall("responseCall");

    public final QRequestCall call;

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath matched = createBoolean("matched");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final com.potato.TutorCall.tutor.domain.QTutor tutor;

    public QResponseCall(String variable) {
        this(ResponseCall.class, forVariable(variable), INITS);
    }

    public QResponseCall(Path<? extends ResponseCall> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QResponseCall(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QResponseCall(PathMetadata metadata, PathInits inits) {
        this(ResponseCall.class, metadata, inits);
    }

    public QResponseCall(Class<? extends ResponseCall> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.call = inits.isInitialized("call") ? new QRequestCall(forProperty("call"), inits.get("call")) : null;
        this.tutor = inits.isInitialized("tutor") ? new com.potato.TutorCall.tutor.domain.QTutor(forProperty("tutor"), inits.get("tutor")) : null;
    }

}

