package com.example.townmarket.board.service;

import com.example.townmarket.board.dto.BoardResponseDto;
import com.example.townmarket.board.dto.BoardRequestDto;
import com.example.townmarket.board.dto.PagingBoardResponse;
import com.example.townmarket.commons.dto.PageDto;
import com.example.townmarket.user.entity.User;
import org.springframework.data.domain.Page;

public interface BoardService {

  void createBoard(BoardRequestDto boardRequestDto, User user);

  void updateBoard(Long boardId, BoardRequestDto boardRequestDto, User user);

  BoardResponseDto getBoard(Long boardId);

  Page<PagingBoardResponse> getBoards(PageDto pageDto);

  void deleteBoard(Long boardId, User user);

}
