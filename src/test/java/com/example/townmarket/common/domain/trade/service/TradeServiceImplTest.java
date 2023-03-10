package com.example.townmarket.common.domain.trade.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.example.townmarket.common.domain.product.repository.ProductRepository;
import com.example.townmarket.common.domain.trade.dto.PagingTrade;
import com.example.townmarket.common.domain.trade.repository.TradeRepository;
import com.example.townmarket.common.domain.user.entity.User;
import com.example.townmarket.common.domain.user.repository.UserRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class TradeServiceImplTest {

  @Mock
  private TradeRepository tradeRepository;

  @Mock
  private UserRepository userRepository;

  @Mock
  private ProductRepository productRepository;

  @InjectMocks
  private TradeServiceImpl tradeService;

  @Test
  @DisplayName("구메 내역 조회 성공")
  void getPurchaseList() {

      // given
      User buyer = User.builder().id(1L).username("John").build();
      Pageable pageable = PageRequest.of(0, 10);
      PagingTrade trade = PagingTrade.builder().userName("Sam").productName("headphone").build();
      Page<PagingTrade> tradePage = new PageImpl<>(List.of(trade), pageable, 1);

      when(tradeRepository.findBuyList(pageable, buyer)).thenReturn(tradePage);

      // when
      Page<PagingTrade> result = tradeService.getPurchaseList(buyer, pageable);

      // then
      assertEquals(tradePage, result);

  }
}