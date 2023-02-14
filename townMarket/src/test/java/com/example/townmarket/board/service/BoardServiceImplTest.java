package com.example.townmarket.board.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.townmarket.board.repository.BoardRepository;
import com.example.townmarket.product.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BoardServiceImplTest {

  @Mock
  BoardRepository boardRepository;
  @Mock
  ProductRepository productRepository;
  @InjectMocks
  BoardServiceImpl boardService;

  @Test
  @DisplayName("게시글 생성 성공 테스트")
  void createBoard() {




  }

  @Test
  void updateBoard() {
  }

  @Test
  void getBoards() {
  }

  @Test
  void getBoard() {
  }

  @Test
  void deleteBoard() {
  }

  @Test
  void findBoardById() {
  }
}