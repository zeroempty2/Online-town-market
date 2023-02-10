package com.example.townmarket.comment.controller;

import com.example.townmarket.comment.dto.CommentRequestDto;
import com.example.townmarket.comment.service.CommentService;
import com.example.townmarket.commons.dto.StatusResponseDto;
import com.example.townmarket.commons.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/comments")
@RestController
public class CommentController {

  private final CommentService commentService;

  // 댓글 생성
  @PostMapping("/board/{boardsId}")
  public ResponseEntity<StatusResponseDto> createComment(@PathVariable Long boardsId,
      @RequestBody CommentRequestDto commentRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new StatusResponseDto(HttpStatus.CREATED.value(),
            commentService.createComment(boardsId, commentRequestDto, userDetails.getUser())));
  }

  // 댓글 불러오기 (전체? 단건?)
//  @GetMapping("/comments/board/{boardsId}")
//  public ResponseEntity<CommentResponseDto> getComment(@PathVariable long commentId) {
//    return ResponseEntity.status(HttpStatus.OK).headers(setHttpHeaders.setHeaderTypeJson())
//        .body(commentService.getComment(commentId));
//  }

  // 댓글 수정
  @PutMapping("/{commentId}/board/{boardsId}}")
  public ResponseEntity<StatusResponseDto> updateComment(@PathVariable Long commentId,
      @PathVariable Long boardsId,
      @RequestBody CommentRequestDto commentRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    return ResponseEntity.status(HttpStatus.OK).body(new StatusResponseDto(HttpStatus.OK.value(),
        commentService.updateComment(commentId, boardsId, commentRequestDto,
            userDetails.getUser())));
  }

  // 댓글 삭제
  @DeleteMapping("/{commentId}/board/{boardsId}")
  public ResponseEntity<StatusResponseDto> deleteComment(@PathVariable Long commentId,
      @PathVariable Long boardsId,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    return ResponseEntity.status(HttpStatus.OK).body(new StatusResponseDto(HttpStatus.OK.value(),
        commentService.deleteComment(commentId, boardsId, userDetails.getUser())));
  }

}









