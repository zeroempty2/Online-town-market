package com.example.townmarket.common.domain.chat.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatRoom is a Querydsl query type for ChatRoom
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatRoom extends EntityPathBase<ChatRoom> {

    private static final long serialVersionUID = 1817195358L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChatRoom chatRoom = new QChatRoom("chatRoom");

    public final com.example.townmarket.common.domain.user.entity.QUser buyer;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final SetPath<ChatMessage, QChatMessage> message = this.<ChatMessage, QChatMessage>createSet("message", ChatMessage.class, QChatMessage.class, PathInits.DIRECT2);

    public final com.example.townmarket.common.domain.product.entity.QProduct product;

    public final StringPath productName = createString("productName");

    public final NumberPath<Long> seller = createNumber("seller", Long.class);

    public QChatRoom(String variable) {
        this(ChatRoom.class, forVariable(variable), INITS);
    }

    public QChatRoom(Path<? extends ChatRoom> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChatRoom(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChatRoom(PathMetadata metadata, PathInits inits) {
        this(ChatRoom.class, metadata, inits);
    }

    public QChatRoom(Class<? extends ChatRoom> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.buyer = inits.isInitialized("buyer") ? new com.example.townmarket.common.domain.user.entity.QUser(forProperty("buyer"), inits.get("buyer")) : null;
        this.product = inits.isInitialized("product") ? new com.example.townmarket.common.domain.product.entity.QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

