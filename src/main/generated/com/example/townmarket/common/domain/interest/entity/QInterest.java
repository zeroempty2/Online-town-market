package com.example.townmarket.common.domain.interest.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInterest is a Querydsl query type for Interest
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInterest extends EntityPathBase<Interest> {

    private static final long serialVersionUID = -994241785L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInterest interest = new QInterest("interest");

    public final com.example.townmarket.common.QTimeStamped _super = new com.example.townmarket.common.QTimeStamped(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.example.townmarket.common.domain.user.entity.QUser interestUser;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final com.example.townmarket.common.domain.product.entity.QProduct product;

    public QInterest(String variable) {
        this(Interest.class, forVariable(variable), INITS);
    }

    public QInterest(Path<? extends Interest> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInterest(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInterest(PathMetadata metadata, PathInits inits) {
        this(Interest.class, metadata, inits);
    }

    public QInterest(Class<? extends Interest> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.interestUser = inits.isInitialized("interestUser") ? new com.example.townmarket.common.domain.user.entity.QUser(forProperty("interestUser"), inits.get("interestUser")) : null;
        this.product = inits.isInitialized("product") ? new com.example.townmarket.common.domain.product.entity.QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

