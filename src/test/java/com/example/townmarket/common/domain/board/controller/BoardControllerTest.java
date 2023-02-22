package com.example.townmarket.common.domain.board.controller;

import static com.example.townmarket.restdocs.ApiDocumentUtils.getDocumentRequest;
import static com.example.townmarket.restdocs.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.townmarket.annotation.WithCustomMockUser;
import com.example.townmarket.common.domain.board.dto.BoardRequestDto;
import com.example.townmarket.common.domain.board.dto.BoardResponseDto;
import com.example.townmarket.common.domain.board.dto.PagingBoardResponse;
import com.example.townmarket.common.domain.board.entity.Board.BoardSubject;
import com.example.townmarket.common.domain.board.service.BoardService;
import com.example.townmarket.common.dto.PageDto;
import com.example.townmarket.common.dto.StatusResponse;
import com.example.townmarket.common.enums.ResponseMessages;
import com.example.townmarket.common.util.SetHttpHeaders;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@WebMvcTest(controllers = BoardController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class BoardControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;


  @MockBean
  BoardService boardService;
  @MockBean
  SetHttpHeaders httpHeaders;

  @Test
  @WithCustomMockUser
  void createBoard() throws Exception {
    BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("글 제목").content("글 내용")
        .subject(BoardSubject.동네소식).build();

    StatusResponse statusResponse = StatusResponse.valueOf(ResponseMessages.CREATED_SUCCESS);
    ResultActions resultActions = mockMvc.perform(post("/boards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(boardRequestDto))
            .with(csrf()))
        .andExpect(status().isCreated());

    resultActions.andDo(document("boardController/createBoard",
        getDocumentRequest(),
        getDocumentResponse(),
        requestFields(
            fieldWithPath("title").type(JsonFieldType.STRING).description("글 제목"),
            fieldWithPath("content").type(JsonFieldType.STRING).description("글 내용"),
            fieldWithPath("subject").type(JsonFieldType.STRING).description("글 카테코리")
        ),
        responseFields(
            fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("상태 반환 코드"),
            fieldWithPath("message").type(JsonFieldType.STRING).description("상태 메시지")
        )
    ));

  }

  @Test
  @WithCustomMockUser
  void updateBoard() throws Exception {
    Long boardId = 1L;
    BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("글 제목").content("글 내용")
        .subject(BoardSubject.동네소식).build();

    StatusResponse statusResponse = StatusResponse.valueOf(ResponseMessages.SUCCESS);
    ResultActions resultActions = mockMvc.perform(put("/boards/{boardId}", boardId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(boardRequestDto))
            .with(csrf()))
        .andExpect(status().isOk());

    resultActions.andDo(document("boardController/updateBoard",
        getDocumentRequest(),
        getDocumentResponse(),
        requestFields(
            fieldWithPath("title").type(JsonFieldType.STRING).description("글 제목"),
            fieldWithPath("content").type(JsonFieldType.STRING).description("글 내용"),
            fieldWithPath("subject").type(JsonFieldType.STRING).description("글 카테코리")
        ),
        responseFields(
            fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("상태 반환 코드"),
            fieldWithPath("message").type(JsonFieldType.STRING).description("상태 메시지")
        )
    ));
  }

  @Test
  @WithCustomMockUser
  void getBoards() throws Exception {
    PagingBoardResponse pagingBoardResponse = PagingBoardResponse.builder().title("글제목")
        .subject(BoardSubject.동네소식).build();

    PageDto pageDto = PageDto.builder().page(1).size(10).isAsc(false).sortBy("title").build();
    Pageable pageable = pageDto.toPageable();

    Page<PagingBoardResponse> pages = new PageImpl<>(Collections.singletonList(pagingBoardResponse),
        pageable, pageable.getPageSize());

    given(boardService.getBoards(pageable)).willReturn(pages);

    ResultActions resultActions = mockMvc.perform(get("/boards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(pageDto))
            .with(csrf()))
        .andExpect(status().isOk());

    resultActions.andDo(document("boardController/getBoards",
        getDocumentRequest(),
        getDocumentResponse(),
        requestFields(
            fieldWithPath("page").type(JsonFieldType.NUMBER).description("페이지"),
            fieldWithPath("size").type(JsonFieldType.NUMBER).description("글의 갯수"),
            fieldWithPath("sortBy").type(JsonFieldType.STRING).description("정렬기준"),
            fieldWithPath("asc").type(JsonFieldType.BOOLEAN).description("오름/내림차순")
        )
    ));
  }

  @Test
  @WithCustomMockUser
  void getBoard() throws Exception {
    Long boardId = 1L;
    BoardResponseDto boardResponseDto = BoardResponseDto.builder()
        .title("글 제목")
        .content("글 내용")
        .comments(any())
        .subject(BoardSubject.동네소식).build();

    given(boardService.getBoard(boardId)).willReturn(boardResponseDto);

    ResultActions resultActions = mockMvc.perform(get("/boards/{boardId}", boardId)
            .with(csrf()))
        .andExpect(status().isOk());

    resultActions.andDo(document("boardController/getBoard",
        getDocumentRequest(),
        getDocumentResponse(),
        responseFields(
            fieldWithPath("title").type(JsonFieldType.STRING).description("글 제목"),
            fieldWithPath("content").type(JsonFieldType.STRING).description("글 내용"),
            fieldWithPath("comments").type(JsonFieldType.NULL).description("댓글"),
            fieldWithPath("createdAt").type(JsonFieldType.NULL).description("생성 일자"),
            fieldWithPath("modifiedAt").type(JsonFieldType.NULL).description("수정 일자"),
            fieldWithPath("subject").type(JsonFieldType.STRING).description("글 카테코리")
        )
    ));
  }

  @Test
  @WithCustomMockUser
  void deleteBoard() throws Exception {
    Long boardId = 1L;
    StatusResponse statusResponse = StatusResponse.valueOf(ResponseMessages.DELETE_SUCCESS);

    ResultActions resultActions = mockMvc.perform(delete("/boards/{boardId}", boardId)
            .with(csrf()))
        .andExpect(status().isNoContent());

    resultActions.andDo(document("boardController/deleteBoard",
        getDocumentRequest(),
        getDocumentResponse(),
        responseFields(
            fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("상태 반환 코드"),
            fieldWithPath("message").type(JsonFieldType.STRING).description("상태 메시지")
        )
    ));

  }
}
