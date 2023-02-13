package com.example.townmarket.board.controller;

import com.example.townmarket.board.dto.BoardResponseDto;
import com.example.townmarket.board.dto.CreateBoardRequestDto;
import com.example.townmarket.board.service.BoardService;
import com.example.townmarket.commons.dto.PageDto;
import com.example.townmarket.commons.dto.StatusResponseDto;
import com.example.townmarket.commons.security.UserDetailsImpl;
import com.example.townmarket.commons.util.SetHttpHeaders;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;

  private final SetHttpHeaders setHttpHeaders;

  // 게시글 생성
  @PostMapping
  public ResponseEntity<StatusResponseDto> createBoard(
      @RequestBody CreateBoardRequestDto createBoardRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new StatusResponseDto(HttpStatus.CREATED.value(), boardService.CreateBoard(
            createBoardRequestDto, userDetails.getUser())));
  }

  // 게시글 수정
  @PutMapping("/{boardId}")
  public ResponseEntity<StatusResponseDto> updateBoard(@PathVariable long boardId,
      @RequestBody CreateBoardRequestDto createBoardRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    return ResponseEntity.status(HttpStatus.OK).body(new StatusResponseDto(HttpStatus.OK.value(),
        boardService.updateBoard(boardId, createBoardRequestDto, userDetails.getUser())));
  }

  // 게시글 단건 조회, 댓글 목록으로 불러오게 추가(페이징)
  @GetMapping("/{boardId}")
  public ResponseEntity<BoardResponseDto> getBoard(@PathVariable long boardId) {
    return ResponseEntity.status(HttpStatus.OK).headers(setHttpHeaders.setHeaderTypeJson())
        .body(boardService.getBoard(boardId));
  }

  // 게시글 전체 조회
  @GetMapping
  public ResponseEntity<Page<BoardResponseDto>> getBoards(@RequestBody PageDto pageDto) {
    return ResponseEntity.status(HttpStatus.OK).body(boardService.getBoards(pageDto));
  }

  // 게시물 삭제
  @DeleteMapping("{boardId}")
  public ResponseEntity<StatusResponseDto> deleteBoard(@PathVariable long boardId,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    return ResponseEntity.status(HttpStatus.OK).body(new StatusResponseDto(HttpStatus.OK.value(),
        boardService.deleteBoard(boardId, userDetails.getUser())));//삭제시는 no contents
  }
}
