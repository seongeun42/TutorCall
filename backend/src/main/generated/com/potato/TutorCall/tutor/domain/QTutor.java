package com.potato.TutorCall.tutor.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTutor is a Querydsl query type for Tutor
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTutor extends EntityPathBase<Tutor> {

    private static final long serialVersionUID = 1876141368L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTutor tutor = new QTutor("tutor");

    public final ListPath<com.potato.TutorCall.qna.domain.Answer, com.potato.TutorCall.qna.domain.QAnswer> answerList = this.<com.potato.TutorCall.qna.domain.Answer, com.potato.TutorCall.qna.domain.QAnswer>createList("answerList", com.potato.TutorCall.qna.domain.Answer.class, com.potato.TutorCall.qna.domain.QAnswer.class, PathInits.DIRECT2);

    public final NumberPath<Double> communicationRate = createNumber("communicationRate", Double.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath introduction = createString("introduction");

    public final BooleanPath isActive = createBoolean("isActive");

    public final ListPath<com.potato.TutorCall.lecture.domain.Lecture, com.potato.TutorCall.lecture.domain.QLecture> lectureList = this.<com.potato.TutorCall.lecture.domain.Lecture, com.potato.TutorCall.lecture.domain.QLecture>createList("lectureList", com.potato.TutorCall.lecture.domain.Lecture.class, com.potato.TutorCall.lecture.domain.QLecture.class, PathInits.DIRECT2);

    public final NumberPath<Double> mannerRate = createNumber("mannerRate", Double.class);

    public final NumberPath<Double> professionalismRate = createNumber("professionalismRate", Double.class);

    public final NumberPath<Integer> reliablity = createNumber("reliablity", Integer.class);

    public final ListPath<com.potato.TutorCall.tutorcall.domain.TutorCall, com.potato.TutorCall.tutorcall.domain.QTutorCall> tutorCallList = this.<com.potato.TutorCall.tutorcall.domain.TutorCall, com.potato.TutorCall.tutorcall.domain.QTutorCall>createList("tutorCallList", com.potato.TutorCall.tutorcall.domain.TutorCall.class, com.potato.TutorCall.tutorcall.domain.QTutorCall.class, PathInits.DIRECT2);

    public final ListPath<TutorTag, QTutorTag> tutorTagList = this.<TutorTag, QTutorTag>createList("tutorTagList", TutorTag.class, QTutorTag.class, PathInits.DIRECT2);

    public final com.potato.TutorCall.user.domain.QUser user;

    public QTutor(String variable) {
        this(Tutor.class, forVariable(variable), INITS);
    }

    public QTutor(Path<? extends Tutor> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTutor(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTutor(PathMetadata metadata, PathInits inits) {
        this(Tutor.class, metadata, inits);
    }

    public QTutor(Class<? extends Tutor> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.potato.TutorCall.user.domain.QUser(forProperty("user")) : null;
    }

}

