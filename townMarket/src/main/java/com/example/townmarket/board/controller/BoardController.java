package com.example.townmarket.board.controller;

import com.example.townmarket.board.dto.BoardResponseDto;
import com.example.townmarket.board.dto.BoardRequestDto;
import com.example.townmarket.board.dto.PagingBoardResponse;
import com.example.townmarket.board.service.BoardService;
import com.example.townmarket.commons.dto.PageDto;
import com.example.townmarket.commons.dto.StatusResponseDto;
import com.example.townmarket.commons.responseMessageData.DefaultResponse;
import com.example.townmarket.commons.responseMessageData.ResponseMessages;
import com.example.townmarket.commons.security.UserDetailsImpl;
import com.example.townmarket.commons.util.SetHttpHeaders;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;
  private final SetHttpHeaders httpHeaders;

  // 게시글 생성
  @PostMapping
  public ResponseEntity<DefaultResponse> createBoard(
      @RequestBody BoardRequestDto boardRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    boardService.createBoard(boardRequestDto, userDetails.getUser());
    DefaultResponse defaultResponse = DefaultResponse.valueOf(ResponseMessages.CREATED_SUCCESS);
    return ResponseEntity.status(HttpStatus.CREATED).body(defaultResponse);
  }

  // 게시글 수정
  @PutMapping("/{boardId}")
  public ResponseEntity<DefaultResponse> updateBoard(@PathVariable Long boardId,
      @RequestBody BoardRequestDto boardRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    boardService.updateBoard(boardId, boardRequestDto, userDetails.getUser());
    DefaultResponse defaultResponse = DefaultResponse.valueOf(ResponseMessages.SUCCESS);
    return ResponseEntity.ok().body(defaultResponse);

  }

  // 게시글 단건 조회, 댓글 목록으로 불러오게 추가(페이징)
  @GetMapping("/{boardId}")
  public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long boardId) {
    HttpHeaders headers = httpHeaders.setHeaderTypeJson();
    return ResponseEntity.ok().headers(headers)
        .body(boardService.getBoard(boardId));
  }

  // 게시글 전체 조회
  @GetMapping
  public ResponseEntity<Page<PagingBoardResponse>> getBoards(@RequestBody PageDto pageDto) {
    HttpHeaders headers = httpHeaders.setHeaderTypeJson();
    return ResponseEntity.ok().headers(headers).body(boardService.getBoards(pageDto));
  }

  // 게시물 삭제
  @DeleteMapping("{boardId}")
  public ResponseEntity<DefaultResponse> deleteBoard(@PathVariable Long boardId,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    boardService.deleteBoard(boardId, userDetails.getUser());
    DefaultResponse defaultResponse = DefaultResponse.valueOf(ResponseMessages.DELETE_SUCCESS);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(defaultResponse);
  }
}
