package com.example.townmarket.common.domain.trade.repository;

import static com.example.townmarket.common.domain.review.entity.QReview.review;
import static com.example.townmarket.common.domain.trade.entity.QTrade.trade;

import com.example.townmarket.common.domain.trade.dto.PagingTrade;
import com.example.townmarket.common.domain.trade.entity.QTrade;
import com.example.townmarket.common.domain.user.entity.User;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

@RequiredArgsConstructor
public class TradeRepositoryQueryImpl implements  TradeRepositoryQuery{

  private final JPAQueryFactory jpaQueryFactory;

  private JPAQuery<PagingTrade> buyQuery() {
    return jpaQueryFactory.select(Projections.constructor(PagingTrade.class,
            trade.buyer.username,
            trade.product.productName))
        .from(trade)
        .setHint("org.hibernate.readOnly", true);
  }
  private JPAQuery<PagingTrade> saleQuery() {
    return jpaQueryFactory.select(Projections.constructor(PagingTrade.class,
            trade.seller.username,
            trade.product.productName))
        .from(trade)
        .setHint("org.hibernate.readOnly", true);
  }

  private JPAQuery<Long> countSellerQuery(User user) {
    return jpaQueryFactory.select(Wildcard.count)
        .from(trade)
        .leftJoin(trade.seller)
        .where(trade.seller.eq(user));
  }

  @Override
  public Page<PagingTrade> findBuyList(Pageable pageable, User buyer) {
    List<PagingTrade> tradeList = buyQuery()
        .where(trade.buyer.eq(buyer))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();
    long countSellerQuery = countSellerQuery(buyer).fetch().get(0);
    return PageableExecutionUtils.getPage(tradeList, pageable, () -> countSellerQuery);
  }

  @Override
  public Page<PagingTrade> findSaleList(Pageable pageable, User seller) {
    List<PagingTrade> tradeList = saleQuery()
        .where(trade.seller.eq(seller))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();
    long countSellerQuery = countSellerQuery(seller).fetch().get(0);
    return PageableExecutionUtils.getPage(tradeList, pageable, () -> countSellerQuery);
  }
}
