package com.example.townmarket.common.domain.review.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserGrade is a Querydsl query type for UserGrade
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserGrade extends EntityPathBase<UserGrade> {

    private static final long serialVersionUID = -1694081375L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserGrade userGrade = new QUserGrade("userGrade");

    public final NumberPath<Integer> grade = createNumber("grade", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QReview review;

    public final com.example.townmarket.common.domain.user.entity.QUser reviewee;

    public QUserGrade(String variable) {
        this(UserGrade.class, forVariable(variable), INITS);
    }

    public QUserGrade(Path<? extends UserGrade> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserGrade(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserGrade(PathMetadata metadata, PathInits inits) {
        this(UserGrade.class, metadata, inits);
    }

    public QUserGrade(Class<? extends UserGrade> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.review = inits.isInitialized("review") ? new QReview(forProperty("review"), inits.get("review")) : null;
        this.reviewee = inits.isInitialized("reviewee") ? new com.example.townmarket.common.domain.user.entity.QUser(forProperty("reviewee"), inits.get("reviewee")) : null;
    }

}

