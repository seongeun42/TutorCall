package com.potato.TutorCall.report.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReport is a Querydsl query type for Report
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReport extends EntityPathBase<Report> {

    private static final long serialVersionUID = 518979992L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReport report = new QReport("report");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath proceedState = createBoolean("proceedState");

    public final NumberPath<Long> reported = createNumber("reported", Long.class);

    public final com.potato.TutorCall.user.domain.QUser reporter;

    public final StringPath result = createString("result");

    public final EnumPath<com.potato.TutorCall.report.domain.enums.ReportType> type = createEnum("type", com.potato.TutorCall.report.domain.enums.ReportType.class);

    public QReport(String variable) {
        this(Report.class, forVariable(variable), INITS);
    }

    public QReport(Path<? extends Report> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReport(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReport(PathMetadata metadata, PathInits inits) {
        this(Report.class, metadata, inits);
    }

    public QReport(Class<? extends Report> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.reporter = inits.isInitialized("reporter") ? new com.potato.TutorCall.user.domain.QUser(forProperty("reporter")) : null;
    }

}

