package com.example.townmarket.board.service;

import com.example.townmarket.board.dto.BoardResponseDto;
import com.example.townmarket.board.dto.BoardRequestDto;
import com.example.townmarket.board.dto.PagingBoardResponse;
import com.example.townmarket.board.entity.Board;
import com.example.townmarket.board.repository.BoardRepository;
import com.example.townmarket.commons.dto.PageDto;
import com.example.townmarket.product.repository.ProductRepository;
import com.example.townmarket.user.entity.User;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

  private final BoardRepository boardRepository;
  private final ProductRepository productRepository;

  // 게시글 생성
  @Override
  @Transactional
  public void createBoard(BoardRequestDto boardRequestDto, User user) {
    Board board = Board.builder()
        .title(boardRequestDto.getTitle())
        .content(boardRequestDto.getContent())
        .subject(boardRequestDto.getSubject())
        .user(user)
        .build();
    boardRepository.save(board);
  }

  // 게시글 수정
  @Override
  @Transactional
  public void updateBoard(Long boardId, BoardRequestDto boardRequestDto,
      User user) {
    Board board = findBoardById(boardId);
    if (!board.checkBoardWriter(user)) {
      throw new IllegalArgumentException("본인 게시글이 아닙니다");
    }
    board.update(boardRequestDto);
  }

  // 게시글 전체 조회
  @Override
  @Transactional
  public Page<PagingBoardResponse> getBoards(PageDto pageDto) {
    Page<Board> boards = boardRepository.findAll(pageDto.toPageable());
    return boards.map(PagingBoardResponse::new);
  }



  // 게시글 단건 조회
  @Override
  @Transactional
  public BoardResponseDto getBoard(Long boardId) {
    Board board = findBoardById(boardId);
    return BoardResponseDto.builder()
        .title(board.getTitle())
        .content(board.getContent())
        .subject(board.getSubject())
        .build();
  }

  // 게시글 삭제
  @Override
  @Transactional
  public void deleteBoard(Long boardId, User user) {
    Board board = findBoardById(boardId);
    if(!board.checkBoardWriter(user)) {
      throw new IllegalArgumentException("본인의 게시글이 아닙니다.");
    }
    productRepository.deleteById(board.getId());

  }

  // 중복 로직 메서드 분리
  public Board findBoardById(Long boardId) {
    return boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));
  }
}
