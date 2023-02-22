package com.example.townmarket.common.domain.board.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.townmarket.common.domain.board.dto.BoardRequestDto;
import com.example.townmarket.common.domain.board.dto.BoardResponseDto;
import com.example.townmarket.common.domain.board.dto.PagingBoardResponse;
import com.example.townmarket.common.domain.board.entity.Board;
import com.example.townmarket.common.domain.board.repository.BoardRepository;
import com.example.townmarket.common.domain.user.entity.User;
import com.example.townmarket.common.dto.PageDto;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class BoardServiceImplTest {

  @Mock
  BoardRepository boardRepository;

  @InjectMocks
  BoardServiceImpl boardService;

  @Test
  @DisplayName("게시글 생성 성공 테스트")
  void createBoard() {
    BoardRequestDto requestDto = BoardRequestDto.builder()
        .title("title1")
        .content("content1")
        .build();

    User user = mock(User.class);

    //when
    boardService.createBoard(requestDto, user);

    //then
    verify(boardRepository).save(isA(Board.class));

  }

  @Test
  @DisplayName("게시글 업데이트 성공 테스트")
  void updateBoard() {
    // given
    User user = mock(User.class);
    Board board = mock(Board.class);
    BoardRequestDto boardRequestDto = mock(BoardRequestDto.class);

    when(boardRepository.findById(board.getId())).thenReturn(Optional.of(board));
    when(Optional.of(board).get().checkBoardWriter(user)).thenReturn(true);

    // when
    boardService.updateBoard(board.getId(), boardRequestDto, user);

    // then
    verify(board).update(boardRequestDto);
  }

  @Test
  @DisplayName("게시글 목록 불러오기 테스트")
  void getBoards() {
    // given
    Pageable pageable = mock(Pageable.class);
    PageDto pageDto = mock(PageDto.class);

    when(pageDto.toPageable()).thenReturn(pageable);
    when(boardRepository.findAll(pageable)).thenReturn(Page.empty());

    // when
    Page<PagingBoardResponse> pagingProductResponse = boardService.getBoards(pageable);

    // then
    assertThat(pagingProductResponse).isNotNull();
  }

  @Test
  @DisplayName("게시글 단건 조회 테스트")
  void getBoard() {
    // given
    BoardRequestDto boardRequest = mock(BoardRequestDto.class);
    Board board = mock(Board.class);

    when(boardRepository.findById(board.getId())).thenReturn(Optional.of(board));

    // when
    BoardResponseDto boardResponse = boardService.getBoard(board.getId());

    // then
    assertThat(boardResponse.getContent()).isEqualTo(boardRequest.getContent());
  }

  @Test
  @DisplayName("게시글 삭제 성공 테스트")
  void deleteBoard() {
    //given
    Board board = mock(Board.class);
    User user = mock(User.class);

    when(boardRepository.findById(board.getId())).thenReturn(Optional.of(board));
    when(Optional.of(board).get().checkBoardWriter(user)).thenReturn(true);

    //when
    boardService.deleteBoard(board.getId(), user);

    //then
    verify(boardRepository).deleteById(board.getId());

  }
}
