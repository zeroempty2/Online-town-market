package com.example.townmarket.board.service;

import com.example.townmarket.board.dto.BoardResponseDto;
import com.example.townmarket.board.dto.CreateBoardRequestDto;
import com.example.townmarket.board.entity.Board;
import com.example.townmarket.board.repository.BoardRepository;
import com.example.townmarket.commons.dto.PageDto;
import com.example.townmarket.user.entity.User;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

  private final BoardRepository boardRepository;

  // 게시글 생성
  @Override
  @Transactional
  public String CreateBoard(CreateBoardRequestDto createBoardRequestDto, User user) {
    Board board = new Board(createBoardRequestDto.getTitle(), createBoardRequestDto.getContent(), createBoardRequestDto.getSubject(), user);
    boardRepository.save(board);
    return "게시글이 성공적으로 생성되었습니다";
  }

  // 게시글 수정
  @Override
  @Transactional
  public String updateBoard(long boardId, CreateBoardRequestDto createBoardRequestDto, User user) {
    Board boardSaved = boardRepository.findById(boardId)
        .orElseThrow(() -> new IllegalArgumentException("게시글 id 없음"));

    if(user.getId().equals(boardSaved.getUserId())) {
      boardSaved.update(createBoardRequestDto.getTitle(), createBoardRequestDto.getContent(),
          createBoardRequestDto.getSubject());
      return "게시글이 수정되었습니다";
    } else throw new IllegalArgumentException("수정이 완료되지 않았습니다");
  }

  // 게시글 전체 조회
  @Override
  @Transactional
  public Page<BoardResponseDto> getBoards(PageDto pageDto) {
    List<Board> boards = findAllBoard();
    return new PageImpl<>(boards.stream().map(BoardResponseDto::new).collect(Collectors.toList()), pageDto.toPageable(),
        pageDto.getSize());

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
    Board boardDelete = boardRepository.findById(boardId)
        .orElseThrow(() -> new IllegalArgumentException("게시글 id 없음"));

    if(user.getId().equals(boardDelete.getUserId())) {
      boardRepository.delete(boardDelete);
      return "삭제가 완료되었습니다";
    } else throw new IllegalArgumentException("삭제가 불가능합니다");
  }

  public List<Board> findAllBoard() {return boardRepository.findAll();}
}
