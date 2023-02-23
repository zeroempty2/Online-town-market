package com.example.townmarket.common.domain.product.repository;

import static com.example.townmarket.common.domain.product.entity.QProduct.product;

import com.example.townmarket.common.domain.product.dto.PagingProductResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class ProductRepositoryQueryImpl implements ProductRepositoryQuery {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  @Transactional(readOnly = true)
  public Page<PagingProductResponse> findAllAndPaging(Pageable pageable) {
    List<PagingProductResponse> pagingProductResponse = jpaQueryFactory
        .select(Projections.constructor(PagingProductResponse.class,
            product.productName
            , product.productPrice))
        .from(product)
        .where(product.block.eq(false))
        .setHint("org.hibernate.readOnly", true)
        .orderBy(product.createdAt.desc())
        .limit(pageable.getPageSize())
        .offset(pageable.getOffset())
        .fetch();
    long totalSize = countQuery().fetch().get(0);

    return PageableExecutionUtils.getPage(pagingProductResponse, pageable, () -> totalSize);
  }

  @Override
  public Page<PagingProductResponse> searchByKeyword(String keyword, Pageable pageable) {
    List<PagingProductResponse> pagingProductResponse = jpaQueryFactory
        .select(Projections.constructor(PagingProductResponse.class,
            product.productName
            , product.productPrice))
        .from(product)
        .where(product.block.eq(false), product.productName.contains(keyword))
        .setHint("org.hibernate.readOnly", true)
        .orderBy(product.createdAt.desc())
        .limit(pageable.getPageSize())
        .offset(pageable.getOffset())
        .fetch();
    long totalSize = countQuery().fetch().get(0);

    return PageableExecutionUtils.getPage(pagingProductResponse, pageable, () -> totalSize);
  }


  private JPAQuery<Long> countQuery() {
    return jpaQueryFactory.select(Wildcard.count)
        .from(product);
  }
}
