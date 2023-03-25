package com.example.townmarket.common.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1910602679L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final com.example.townmarket.common.QTimeStamped _super = new com.example.townmarket.common.QTimeStamped(this);

    public final ListPath<com.example.townmarket.common.domain.address.entity.Address, com.example.townmarket.common.domain.address.entity.QAddress> address = this.<com.example.townmarket.common.domain.address.entity.Address, com.example.townmarket.common.domain.address.entity.QAddress>createList("address", com.example.townmarket.common.domain.address.entity.Address.class, com.example.townmarket.common.domain.address.entity.QAddress.class, PathInits.DIRECT2);

    public final SetPath<com.example.townmarket.common.domain.board.entity.Board, com.example.townmarket.common.domain.board.entity.QBoard> boards = this.<com.example.townmarket.common.domain.board.entity.Board, com.example.townmarket.common.domain.board.entity.QBoard>createSet("boards", com.example.townmarket.common.domain.board.entity.Board.class, com.example.townmarket.common.domain.board.entity.QBoard.class, PathInits.DIRECT2);

    public final SetPath<com.example.townmarket.common.domain.trade.entity.Trade, com.example.townmarket.common.domain.trade.entity.QTrade> buyer = this.<com.example.townmarket.common.domain.trade.entity.Trade, com.example.townmarket.common.domain.trade.entity.QTrade>createSet("buyer", com.example.townmarket.common.domain.trade.entity.Trade.class, com.example.townmarket.common.domain.trade.entity.QTrade.class, PathInits.DIRECT2);

    public final SetPath<com.example.townmarket.common.domain.chat.entity.ChatRoom, com.example.townmarket.common.domain.chat.entity.QChatRoom> chatRooms = this.<com.example.townmarket.common.domain.chat.entity.ChatRoom, com.example.townmarket.common.domain.chat.entity.QChatRoom>createSet("chatRooms", com.example.townmarket.common.domain.chat.entity.ChatRoom.class, com.example.townmarket.common.domain.chat.entity.QChatRoom.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final SetPath<com.example.townmarket.common.domain.review.entity.UserGrade, com.example.townmarket.common.domain.review.entity.QUserGrade> grades = this.<com.example.townmarket.common.domain.review.entity.UserGrade, com.example.townmarket.common.domain.review.entity.QUserGrade>createSet("grades", com.example.townmarket.common.domain.review.entity.UserGrade.class, com.example.townmarket.common.domain.review.entity.QUserGrade.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final SetPath<com.example.townmarket.common.domain.interest.entity.Interest, com.example.townmarket.common.domain.interest.entity.QInterest> interests = this.<com.example.townmarket.common.domain.interest.entity.Interest, com.example.townmarket.common.domain.interest.entity.QInterest>createSet("interests", com.example.townmarket.common.domain.interest.entity.Interest.class, com.example.townmarket.common.domain.interest.entity.QInterest.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath password = createString("password");

    public final SetPath<com.example.townmarket.common.domain.product.entity.Product, com.example.townmarket.common.domain.product.entity.QProduct> products = this.<com.example.townmarket.common.domain.product.entity.Product, com.example.townmarket.common.domain.product.entity.QProduct>createSet("products", com.example.townmarket.common.domain.product.entity.Product.class, com.example.townmarket.common.domain.product.entity.QProduct.class, PathInits.DIRECT2);

    public final QProfile profile;

    public final SetPath<com.example.townmarket.common.domain.report.entity.UserReport, com.example.townmarket.common.domain.report.entity.QUserReport> reports = this.<com.example.townmarket.common.domain.report.entity.UserReport, com.example.townmarket.common.domain.report.entity.QUserReport>createSet("reports", com.example.townmarket.common.domain.report.entity.UserReport.class, com.example.townmarket.common.domain.report.entity.QUserReport.class, PathInits.DIRECT2);

    public final EnumPath<com.example.townmarket.common.enums.RoleEnum> role = createEnum("role", com.example.townmarket.common.enums.RoleEnum.class);

    public final SetPath<com.example.townmarket.common.domain.trade.entity.Trade, com.example.townmarket.common.domain.trade.entity.QTrade> seller = this.<com.example.townmarket.common.domain.trade.entity.Trade, com.example.townmarket.common.domain.trade.entity.QTrade>createSet("seller", com.example.townmarket.common.domain.trade.entity.Trade.class, com.example.townmarket.common.domain.trade.entity.QTrade.class, PathInits.DIRECT2);

    public final SetPath<com.example.townmarket.common.domain.review.entity.Review, com.example.townmarket.common.domain.review.entity.QReview> sendReviews = this.<com.example.townmarket.common.domain.review.entity.Review, com.example.townmarket.common.domain.review.entity.QReview>createSet("sendReviews", com.example.townmarket.common.domain.review.entity.Review.class, com.example.townmarket.common.domain.review.entity.QReview.class, PathInits.DIRECT2);

    public final StringPath username = createString("username");

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.profile = inits.isInitialized("profile") ? new QProfile(forProperty("profile")) : null;
    }

}

