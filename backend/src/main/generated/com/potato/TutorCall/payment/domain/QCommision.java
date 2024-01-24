package com.potato.TutorCall.payment.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommision is a Querydsl query type for Commision
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommision extends EntityPathBase<Commision> {

    private static final long serialVersionUID = 518242884L;

    public static final QCommision commision1 = new QCommision("commision1");

    public final NumberPath<Double> commision = createNumber("commision", Double.class);

    public final NumberPath<Integer> grade = createNumber("grade", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QCommision(String variable) {
        super(Commision.class, forVariable(variable));
    }

    public QCommision(Path<? extends Commision> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommision(PathMetadata metadata) {
        super(Commision.class, metadata);
    }

}

