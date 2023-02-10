package com.example.townmarket.board.service;

import com.example.townmarket.board.dto.BoardResponseDto;
import com.example.townmarket.board.dto.CreateBoardRequestDto;
import com.example.townmarket.commons.dto.PageDto;
import com.example.townmarket.user.entity.User;
import org.springframework.data.domain.Page;

public interface BoardService {

  String CreateBoard(CreateBoardRequestDto createBoardRequestDto, User user);

  String updateBoard(long boardId, CreateBoardRequestDto createBoardRequestDto, User user);

  BoardResponseDto getBoard(long boardId);

  Page<BoardResponseDto> getBoards(PageDto pageDto);

  String deleteBoard(long boardId, User user);

}
