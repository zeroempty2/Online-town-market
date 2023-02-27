package com.example.townmarket.common.domain.chat.repository;

import static com.example.townmarket.common.domain.chat.entity.QChatRoom.chatRoom;
import static com.example.townmarket.common.domain.product.entity.QProduct.product;

import com.example.townmarket.common.domain.chat.dto.ChatRoomResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class ChatRoomRepositoryQueryImpl implements ChatRoomRepositoryQuery {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  @Transactional(readOnly = true)
  public ChatRoomResponse searchChatRoomByChatRoomId(Long chatRoomId) {
    return jpaQueryFactory.select(Projections.constructor(ChatRoomResponse.class,
            chatRoom.product.id,
            chatRoom.product.user.profile.img_url,
            chatRoom.product.user.profile.nickName,
            chatRoom.product.user.region,
            chatRoom.product.productName,
            chatRoom.id
        )).from(chatRoom)
        .where(chatRoom.id.eq(chatRoomId))
        .leftJoin(chatRoom.product).fetchJoin()
        .leftJoin(product.user).fetchJoin()
        .setHint("org.hibernate.readOnly", true)
        .fetchFirst();
  }
}
