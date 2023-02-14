package com.example.townmarket.board.service;

import com.example.townmarket.board.dto.BoardResponseDto;
import com.example.townmarket.board.dto.CreateBoardRequestDto;
import com.example.townmarket.board.entity.Board;
import com.example.townmarket.board.repository.BoardRepository;
import com.example.townmarket.commons.dto.PageDto;
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

  // 게시글 생성
  @Override
  @Transactional
  public String CreateBoard(CreateBoardRequestDto createBoardRequestDto, User user) {
    Board board = new Board(createBoardRequestDto.getTitle(), createBoardRequestDto.getContent(),
        createBoardRequestDto.getSubject(), user);//빌더 사용 고려
    boardRepository.save(board);
    return "게시글이 성공적으로 생성되었습니다";
  }

  // 게시글 수정
  @Override
  @Transactional
  public String updateBoard(long boardId, CreateBoardRequestDto createBoardRequestDto,
      User user) {  //CreateBoardRequestDto -> BoardRequestDto
    Board board = boardRepository.findById(boardId)
        .orElseThrow(() -> new IllegalArgumentException("게시글 id 없음"));  //메서드로 분리해서 사용

    if (board.checkBoardWriter(user)) {
      board.update(createBoardRequestDto);
      return "게시글이 수정되었습니다";
    } else {
      throw new IllegalArgumentException("수정이 완료되지 않았습니다");
    }
  }

  // 게시글 전체 조회
  @Override
  @Transactional
  public Page<BoardResponseDto> getBoards(PageDto pageDto) {
    Page<Board> boards = boardRepository.findAll(pageDto.toPageable());
    return boards.map(BoardResponseDto::new);
  }

  // 게시글 단건 조회
  @Override
  @Transactional
  public BoardResponseDto getBoard(long boardId) {
    Board board = boardRepository.findById(boardId)
        .orElseThrow(() -> new IllegalArgumentException("게시글 id 없음"));

    return new BoardResponseDto(board);
  }

  // 게시글 삭제
  @Override
  @Transactional
  public String deleteBoard(long boardId, User user) {
    Board board = boardRepository.findById(boardId)
        .orElseThrow(() -> new IllegalArgumentException("게시글 id 없음"));

    if (board.checkBoardWriter(user)) {
      boardRepository.delete(board);
      return "삭제가 완료되었습니다";
    } else {
      throw new IllegalArgumentException("삭제가 불가능합니다");
    }
  }

  public List<Board> findAllBoard() {
    return boardRepository.findAll();
  }
}
