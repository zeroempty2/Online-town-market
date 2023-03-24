package com.example.townmarket.common.domain.review.entity;

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

    private static final long serialVersionUID = -726811741L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReview review = new QReview("review");

    public final com.example.townmarket.common.QTimeStamped _super = new com.example.townmarket.common.QTimeStamped(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final com.example.townmarket.common.domain.product.entity.QProduct product;

    public final StringPath reviewContents = createString("reviewContents");

    public final com.example.townmarket.common.domain.user.entity.QUser reviewer;

    public final QUserGrade userGrade;

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
        this.product = inits.isInitialized("product") ? new com.example.townmarket.common.domain.product.entity.QProduct(forProperty("product"), inits.get("product")) : null;
        this.reviewer = inits.isInitialized("reviewer") ? new com.example.townmarket.common.domain.user.entity.QUser(forProperty("reviewer"), inits.get("reviewer")) : null;
        this.userGrade = inits.isInitialized("userGrade") ? new QUserGrade(forProperty("userGrade"), inits.get("userGrade")) : null;
    }

}

