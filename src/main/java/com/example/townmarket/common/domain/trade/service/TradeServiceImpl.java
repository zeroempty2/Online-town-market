package com.example.townmarket.common.domain.trade.service;

import com.example.townmarket.common.domain.product.entity.Product;
import com.example.townmarket.common.domain.product.repository.ProductRepository;
import com.example.townmarket.common.domain.trade.dto.CreateTradeDto;
import com.example.townmarket.common.domain.trade.dto.PagingTrade;
import com.example.townmarket.common.domain.trade.entity.Trade;
import com.example.townmarket.common.domain.trade.repository.TradeRepository;
import com.example.townmarket.common.domain.user.entity.User;
import com.example.townmarket.common.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TradeServiceImpl implements TradeService {

  private final TradeRepository tradeRepository;
  private final UserRepository userRepository;
  private final ProductRepository productRepository;

  @Override
  public Page<PagingTrade> getPurchaseList(User buyer, Pageable pageable) {
    Page<Trade> tradePage = tradeRepository.findAllByBuyer(pageable, buyer.getId());
    return tradePage.map(PagingTrade::getBuyList);
  }

  @Override
  public Page<PagingTrade> getSalesList(User seller, Pageable pageable) {
    Page<Trade> tradePage = tradeRepository.findAllByBuyer(pageable, seller.getId());
    return tradePage.map(PagingTrade::getSellerList);
  }

  @Override
  public Page<PagingTrade> getSalesListOfOther(Long userId, Pageable pageable) {
    Page<Trade> tradePage = tradeRepository.findAllBySeller(pageable, userId);
    return tradePage.map(PagingTrade::getSellerList);
  }

  @Override
  public void createTrade(CreateTradeDto createTrade, User seller) {
    Product product = findByProductId(createTrade);
    product.updateProductEnum();
    User buyer = findByUserId(createTrade);
    Trade trade = Trade.builder().buyer(buyer).seller(seller).product(product).build();
    tradeRepository.save(trade);
  }

  private User findByUserId(CreateTradeDto createTrade) {
    return userRepository.findById(createTrade.getBuyerId())
        .orElseThrow(() -> new IllegalArgumentException("해당 유저는 존재하지않습니다"));
  }

  private Product findByProductId(CreateTradeDto createTrade) {
    return productRepository.findById(createTrade.getProductId())
        .orElseThrow(() -> new IllegalArgumentException("해당 상품을 찾을수 없습니다"));
  }


}

