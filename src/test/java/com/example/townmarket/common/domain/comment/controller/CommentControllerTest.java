package com.example.townmarket.common.domain.comment.controller;

import static com.example.townmarket.restdocs.ApiDocumentUtils.getDocumentRequest;
import static com.example.townmarket.restdocs.ApiDocumentUtils.getDocumentResponse;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.townmarket.annotation.WithCustomMockUser;
import com.example.townmarket.common.domain.comment.dto.CommentRequestDto;
import com.example.townmarket.common.domain.comment.entity.Comment;
import com.example.townmarket.common.domain.comment.service.CommentService;
import com.example.townmarket.common.domain.review.controller.ReviewController;
import com.example.townmarket.common.domain.user.entity.User;
import com.example.townmarket.common.dto.StatusResponse;
import com.example.townmarket.common.enums.ResponseMessages;
import com.example.townmarket.common.security.UserDetailsImpl;
import com.example.townmarket.common.util.SetHttpHeaders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@WebMvcTest(controllers = CommentController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class CommentControllerTest {
  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @MockBean
  SetHttpHeaders setHttpHeaders;

  @MockBean
  CommentService commentService;

  @Test
  @WithCustomMockUser
  void createComment() throws Exception {
    Long boardsId = 1L;

    CommentRequestDto commentRequestDto = new CommentRequestDto("댓글 생성");

    UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
        .getAuthentication().getPrincipal();

    User user = userDetails.getUser();

    StatusResponse statusResponse = StatusResponse.valueOf(ResponseMessages.CREATED_SUCCESS);

    ResultActions resultActions = mockMvc.perform(post("/comments/board/{boardsId}", boardsId)
            .content(objectMapper.writeValueAsBytes(commentRequestDto))
            .contentType(MediaType.APPLICATION_JSON)
            .with(csrf()))
        .andExpect(status().isCreated());

    resultActions.andDo(document("commentController/createComment",
        getDocumentRequest(),
        getDocumentResponse(),
        requestFields(
            fieldWithPath("commentContents").description(JsonFieldType.OBJECT).description("댓글 생성")
        ),
        responseFields(
            fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("상태 반환 코드"),
            fieldWithPath("message").type(JsonFieldType.STRING).description("상태 메시지")
        )
    ));


  }

  @Test
  @WithCustomMockUser
  void updateComment() throws Exception {

    Long commentId = 1L;

    CommentRequestDto commentRequestDto = new CommentRequestDto("댓글 수정");

    UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
        .getAuthentication().getPrincipal();

    User user = userDetails.getUser();

    StatusResponse statusResponse = StatusResponse.valueOf(ResponseMessages.SUCCESS);

    ResultActions resultActions = mockMvc.perform(put("/comments/{commentId}", commentId)
            .content(objectMapper.writeValueAsBytes(commentRequestDto))
            .contentType(MediaType.APPLICATION_JSON)
            .with(csrf()))
        .andExpect(status().isOk());

    resultActions.andDo(document("commentController/updateComment",
        getDocumentRequest(),
        getDocumentResponse(),
        requestFields(
            fieldWithPath("commentContents").description(JsonFieldType.OBJECT).description("댓글 수정")
        ),
        responseFields(
            fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("상태 반환 코드"),
            fieldWithPath("message").type(JsonFieldType.STRING).description("상태 메시지")
        )
    ));
  }

  @Test
  @WithCustomMockUser
  void deleteComment() throws Exception{
    Long commentId = 1L;

    UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
        .getAuthentication().getPrincipal();

    User user = userDetails.getUser();

    StatusResponse statusResponse = StatusResponse.valueOf(ResponseMessages.DELETE_SUCCESS);

    ResultActions resultActions = mockMvc.perform(delete("/comments/{commentId}", commentId)
            .with(csrf()))
        .andExpect(status().isNoContent());

    resultActions.andDo(document("commentController/deleteComment",
        getDocumentResponse(),
        responseFields(
            fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("상태 반환 코드"),
            fieldWithPath("message").type(JsonFieldType.STRING).description("상태 메시지")
        )
    ));
  }
}
