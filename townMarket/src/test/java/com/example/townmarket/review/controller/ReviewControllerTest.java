package com.example.townmarket.review.controller;


import static org.mockito.Mockito.mock;
import static com.example.townmarket.restdocs.ApiDocumentUtils.getDocumentRequest;
import static com.example.townmarket.restdocs.ApiDocumentUtils.getDocumentResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.townmarket.annotation.WithCustomMockUser;
import com.example.townmarket.common.domain.review.controller.ReviewController;
import com.example.townmarket.common.dto.PageDto;
import com.example.townmarket.common.responseMessageData.DefaultResponse;
import com.example.townmarket.common.enums.ResponseMessages;
import com.example.townmarket.common.util.SetHttpHeaders;
import com.example.townmarket.common.domain.review.dto.CreateReviewRequestDto;
import com.example.townmarket.common.domain.review.dto.ReviewResponseDto;
import com.example.townmarket.common.domain.review.dto.UpdateReviewRequestDto;
import com.example.townmarket.common.domain.review.service.ReviewServiceImpl;
import com.example.townmarket.common.domain.user.entity.Profile;
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
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@WebMvcTest(controllers = ReviewController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class ReviewControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @MockBean
  ReviewServiceImpl reviewService;

  @MockBean
  SetHttpHeaders setHttpHeaders;

  @Test
  @WithCustomMockUser
  void createReview() throws Exception {
    CreateReviewRequestDto createReviewRequestDto = CreateReviewRequestDto.builder()
        .revieweeId(1L)
        .grade(1)
        .productId(1L)
        .reviewContents("reviewContents")
        .build();

    DefaultResponse defaultResponse = DefaultResponse.valueOf(ResponseMessages.CREATED_SUCCESS);

    ResultActions resultActions = mockMvc.perform(post("/review")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(createReviewRequestDto))
            .accept(MediaType.APPLICATION_JSON)
            .with(csrf()))
        .andExpect(status().isCreated());

    resultActions.andDo(document("reviewController/createReview",
        getDocumentRequest(),
        getDocumentResponse(),
        requestFields(
            fieldWithPath("revieweeId").type(JsonFieldType.NUMBER).description("리뷰_작성자"),
            fieldWithPath("grade").type(JsonFieldType.NUMBER).description("평가_점수"),
            fieldWithPath("productId").type(JsonFieldType.NUMBER).description("상품_ID"),
            fieldWithPath("review").type(JsonFieldType.STRING).description("리뷰_내용")
        ),
        responseFields(
            fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("상태 반환 코드"),
            fieldWithPath("message").type(JsonFieldType.STRING).description("상태 메시지")
        )
    ));
  }

  @Test
  @WithCustomMockUser
  void showSelectReview() throws Exception {
    Long reviewId = 1L;
    ReviewResponseDto reviewResponseDto = ReviewResponseDto.builder()
        .reviewContents("review")
        .reviewerProfile(new Profile("reviewer", "img1"))
        .revieweeProfile(new Profile("reviewee", "img2"))
        .productName("productName1")
        .build();

    ResultActions resultActions = mockMvc.perform(get("/review/{reviewId}", reviewId)
            .with(csrf()))
        .andExpect(status().isOk());

    resultActions.andDo(document("reviewController/showSelectReview",
        getDocumentRequest(),
        getDocumentResponse()
//        requestFields(
//            fieldWithPath("reviewId").type(JsonFieldType.NUMBER).description("조회하고자 하는 리뷰ID")
//        ),
//        responseFields(
//            fieldWithPath("review").type(JsonFieldType.STRING).description("리뷰내용"),
//            fieldWithPath("reviewerProfile").type(JsonFieldType.OBJECT).description("리뷰작성자_프로필"),
//            fieldWithPath("reviewerProfile.nickName").type(JsonFieldType.STRING)
//                .description("리뷰작성자_프로필_닉네임"),
//            fieldWithPath("reviewerProfile.img_url").type(JsonFieldType.STRING)
//                .description("리뷰작성자_프로필_이미지"),
//            fieldWithPath("revieweeProfile").type(JsonFieldType.OBJECT).description("판매자_프로필"),
//            fieldWithPath("revieweeProfile.nickName").type(JsonFieldType.STRING)
//                .description("판매자프로필_닉네임"),
//            fieldWithPath("revieweeProfile.img_url").type(JsonFieldType.STRING)
//                .description("판매자프로필_이미지"),
//            fieldWithPath("productName").type(JsonFieldType.STRING).description("상품이름")
//        )
    ));
  }

  @Test
  @WithCustomMockUser
  void showMyReviews() throws Exception {
    PageDto pageDto = PageDto.builder().page(1).size(1).build();

    ReviewResponseDto reviewResponseDto = ReviewResponseDto.builder()
        .reviewContents("review")
        .reviewerProfile(new Profile("reviewer", "img1"))
        .revieweeProfile(new Profile("reviewee", "img2"))
        .productName("productName1")
        .build();
    Page<ReviewResponseDto> reviewResponseDtoPage = new PageImpl<>(
        Collections.singletonList(reviewResponseDto));

//    when(reviewService.showMyReviews(pageDto, any())).thenReturn(reviewResponseDtoPage);

    ResultActions resultActions = mockMvc.perform(get("/reviews")
            .param("page", "0")
            .param("size", "10")
            .with(csrf()))
        .andExpect(status().isOk());

    resultActions.andDo(document("reviewController/showMyReviews"));
//        getDocumentRequest(),
//        getDocumentResponse(),
//        responseFields(
//            fieldWithPath("content.[].review").type(JsonFieldType.STRING).description("리뷰내용"),
//            fieldWithPath("content.[].reviewerProfile").type(JsonFieldType.OBJECT)
//                .description("리뷰작성자_프로필"),
//            fieldWithPath("content.[].reviewerProfile.nickName").type(JsonFieldType.STRING)
//                .description("리뷰작성자_프로필_닉네임"),
//            fieldWithPath("content.[].reviewerProfile.img_url").type(JsonFieldType.STRING)
//                .description("리뷰작성자_프로필_이미지"),
//            fieldWithPath("content.[].revieweeProfile").type(JsonFieldType.OBJECT)
//                .description("판매자_프로필"),
//            fieldWithPath("content.[].revieweeProfile.nickName").type(JsonFieldType.STRING)
//                .description("판매자프로필_닉네임"),
//            fieldWithPath("content.[].revieweeProfile.img_url").type(JsonFieldType.STRING)
//                .description("판매자프로필_이미지"),
//            fieldWithPath("content.[].productName").type(JsonFieldType.STRING).description("상품이름"),
//            fieldWithPath("totalPages").type(JsonFieldType.NUMBER).description("총 페이지수"))));
  }

  @Test
  @WithCustomMockUser
  void updateMyReview() throws Exception {
    Long reviewId = 1L;
    UpdateReviewRequestDto updateReviewRequestDto = UpdateReviewRequestDto.builder()
        .reviewId(1L)
        .reviewContents("reviewContents")
        .grade(1).build();

    mockMvc.perform(patch("/reviews/update/{reviewId}", reviewId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(updateReviewRequestDto))
            .accept(MediaType.APPLICATION_JSON)
            .with(csrf()))
        .andExpect(status().isOk());
  }

  @Test
  @WithCustomMockUser
  void deleteMyReview() throws Exception {
    Long reviewId = 1L;
    mockMvc.perform(delete("/reviews/delete/{reviewId}", reviewId)
            .with(csrf()))
        .andExpect(status().isNoContent());

  }
}
