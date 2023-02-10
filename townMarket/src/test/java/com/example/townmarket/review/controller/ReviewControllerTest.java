package com.example.townmarket.review.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.townmarket.annotation.WithCustomMockUser;
import com.example.townmarket.commons.dto.PageDto;
import com.example.townmarket.commons.util.SetHttpHeaders;
import com.example.townmarket.review.dto.CreateReviewRequestDto;
import com.example.townmarket.review.dto.UpdateReviewRequestDto;
import com.example.townmarket.review.service.ReviewServiceImpl;
import com.example.townmarket.user.controller.UserController;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
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
  ReviewServiceImpl ReviewService;

  @MockBean
  SetHttpHeaders setHttpHeaders;

  @Test
  @WithCustomMockUser
  void createReview() throws Exception {
    CreateReviewRequestDto createReviewRequestDto = CreateReviewRequestDto.builder()
        .revieweeId(1L)
        .grade(1)
        .productId(1L)
        .review("review")
        .build();

    ResultActions resultActions = mockMvc.perform(post("/review")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(createReviewRequestDto))
            .accept(MediaType.APPLICATION_JSON)
            .with(csrf()))
        .andExpect(status().isCreated());
  }

  @Test
  @WithCustomMockUser
  void showSelectReview() throws Exception {
    Long reviewId = 1L;
    ResultActions resultActions = mockMvc.perform(get("/review/{reviewId}", reviewId)
            .with(csrf()))
        .andExpect(status().isOk());
  }

  @Test
  @WithCustomMockUser
  void showMyReviews() throws Exception {
    PageDto pageDto = mock(PageDto.class);
    mockMvc.perform(get("/reviews")
            .with(csrf()))
        .andExpect(status().isOk());
  }

  @Test
  @WithCustomMockUser
  void updateMyReview() throws Exception {
    Long reviewId = 1L;
    UpdateReviewRequestDto updateReviewRequestDto = UpdateReviewRequestDto.builder()
        .reviewId(1L)
        .review("review")
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
