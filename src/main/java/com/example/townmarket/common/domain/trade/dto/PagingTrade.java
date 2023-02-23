package com.example.townmarket.common.domain.trade.dto;

import com.example.townmarket.common.domain.trade.entity.Trade;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PagingTrade {

  private String userName;

  private String productName;

  public static PagingTrade getBuyList(Trade trade){
    PagingTrade pagingTrade = PagingTrade.builder()
        .userName(trade.getBuyer().getUsername())
        .productName(trade.getProduct().getProductName())
        .build();
    return pagingTrade;
  }

  public static PagingTrade getSellerList(Trade trade){
    PagingTrade pagingTrade = PagingTrade.builder()
        .userName(trade.getSeller().getUsername())
        .productName(trade.getProduct().getProductName())
        .build();
    return pagingTrade;
  }


}
