package com.example.townmarket.common.domain.product.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = 289295097L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduct product = new QProduct("product");

    public final com.example.townmarket.common.QTimeStamped _super = new com.example.townmarket.common.QTimeStamped(this);

    public final BooleanPath block = createBoolean("block");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final SetPath<com.example.townmarket.common.domain.interest.entity.Interest, com.example.townmarket.common.domain.interest.entity.QInterest> interest = this.<com.example.townmarket.common.domain.interest.entity.Interest, com.example.townmarket.common.domain.interest.entity.QInterest>createSet("interest", com.example.townmarket.common.domain.interest.entity.Interest.class, com.example.townmarket.common.domain.interest.entity.QInterest.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final EnumPath<Product.ProductCategory> productCategory = createEnum("productCategory", Product.ProductCategory.class);

    public final StringPath productContents = createString("productContents");

    public final EnumPath<Product.ProductEnum> productEnum = createEnum("productEnum", Product.ProductEnum.class);

    public final StringPath productImg = createString("productImg");

    public final StringPath productName = createString("productName");

    public final NumberPath<Long> productPrice = createNumber("productPrice", Long.class);

    public final EnumPath<Product.ProductStatus> productStatus = createEnum("productStatus", Product.ProductStatus.class);

    public final SetPath<com.example.townmarket.common.domain.chat.entity.ChatRoom, com.example.townmarket.common.domain.chat.entity.QChatRoom> room = this.<com.example.townmarket.common.domain.chat.entity.ChatRoom, com.example.townmarket.common.domain.chat.entity.QChatRoom>createSet("room", com.example.townmarket.common.domain.chat.entity.ChatRoom.class, com.example.townmarket.common.domain.chat.entity.QChatRoom.class, PathInits.DIRECT2);

    public final com.example.townmarket.common.domain.user.entity.QUser user;

    public final NumberPath<Long> viewCount = createNumber("viewCount", Long.class);

    public QProduct(String variable) {
        this(Product.class, forVariable(variable), INITS);
    }

    public QProduct(Path<? extends Product> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProduct(PathMetadata metadata, PathInits inits) {
        this(Product.class, metadata, inits);
    }

    public QProduct(Class<? extends Product> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.example.townmarket.common.domain.user.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

