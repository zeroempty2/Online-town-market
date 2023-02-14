package com.example.townmarket.common.domain.board.service;

import com.example.townmarket.common.domain.board.dto.BoardResponseDto;
import com.example.townmarket.common.domain.board.dto.BoardRequestDto;
import com.example.townmarket.common.domain.board.dto.PagingBoardResponse;
import com.example.townmarket.common.domain.board.entity.Board;
import com.example.townmarket.common.dto.PageDto;
import com.example.townmarket.common.domain.user.entity.User;
import org.springframework.data.domain.Page;

public interface BoardService {

  void createBoard(BoardRequestDto boardRequestDto, User user);

  void updateBoard(Long boardId, BoardRequestDto boardRequestDto, User user);

  BoardResponseDto getBoard(Long boardId);

  Page<PagingBoardResponse> getBoards(PageDto pageDto);

  void deleteBoard(Long boardId, User user);

  Board findBoardById(Long boardsId);
}
