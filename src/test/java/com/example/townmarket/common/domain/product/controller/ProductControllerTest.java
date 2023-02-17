package com.example.townmarket.common.domain.product.controller;


import static com.example.townmarket.restdocs.ApiDocumentUtils.getDocumentRequest;
import static com.example.townmarket.restdocs.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.townmarket.annotation.WithCustomMockUser;
import com.example.townmarket.common.domain.product.controller.ProductController;
import com.example.townmarket.common.domain.product.dto.PagingProductResponse;
import com.example.townmarket.common.domain.product.dto.ProductRequestDto;
import com.example.townmarket.common.domain.product.dto.ProductResponseDto;
import com.example.townmarket.common.domain.product.entity.Product.ProductCategory;
import com.example.townmarket.common.domain.product.entity.Product.ProductEnum;
import com.example.townmarket.common.domain.product.entity.Product.ProductStatus;
import com.example.townmarket.common.domain.product.service.ProductService;
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
@WebMvcTest(controllers = ProductController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class ProductControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @MockBean
  ProductService productService;

  @MockBean
  SetHttpHeaders setHttpHeaders;


  @Test
  @WithCustomMockUser
  void addProduct() throws Exception {
    ProductRequestDto productRequestDto = ProductRequestDto.builder()
        .productName("productName")
        .productPrice(1000)
        .productStatus(ProductStatus.S)
        .productCategory(ProductCategory.CAR)
        .productEnum(ProductEnum.나눔)
        .build();

    StatusResponse statusResponse = StatusResponse.valueOf(ResponseMessages.CREATED_SUCCESS);

    ResultActions resultActions = mockMvc.perform(
            post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(productRequestDto))
                .with(csrf()))
        .andExpect(status().isCreated());

    resultActions.andDo(document("productController/addProduct",
        getDocumentRequest(),
        getDocumentResponse(),
        requestFields(
            fieldWithPath("productName").description(JsonFieldType.STRING).description("상품명"),
            fieldWithPath("productPrice").description(JsonFieldType.NUMBER).description("상품가격"),
            fieldWithPath("productStatus").description(JsonFieldType.OBJECT).description("상품상태"),
            fieldWithPath("productCategory").description(JsonFieldType.OBJECT)
                .description("상품 카테고리"),
            fieldWithPath("productEnum").description(JsonFieldType.OBJECT).description("상품 거래 상태")
        ),
        responseFields(
            fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("상태 반환 코드"),
            fieldWithPath("message").type(JsonFieldType.STRING).description("상태 메시지")
        )
    ));
  }

  @Test
  @WithCustomMockUser
  void getProduct() throws Exception {

    Long productId = 1L;

    ProductResponseDto productResponseDto = ProductResponseDto.builder()
        .productId(1L)
        .productName("productName")
        .productPrice(1000)
        .productStatus(ProductStatus.S)
        .productCategory(ProductCategory.CAR)
        .productEnum(ProductEnum.나눔)
        .build();
    given(productService.showProduct(productId)).willReturn(productResponseDto);

    ResultActions resultActions = mockMvc.perform(get("/products/{productId}", productId)
        .with(csrf()));

    resultActions.andExpect(status().isOk());
    resultActions.andDo(document("productController/getProductId",
        getDocumentRequest(),
        getDocumentResponse(),
        responseFields(
            fieldWithPath("createdAt").description(JsonFieldType.OBJECT).description("생성일자"),
            fieldWithPath("modifiedAt").description(JsonFieldType.OBJECT).description("수정일자"),
            fieldWithPath("productId").description(JsonFieldType.NUMBER).description("상품명"),
            fieldWithPath("productName").description(JsonFieldType.STRING).description("상품명"),
            fieldWithPath("productPrice").description(JsonFieldType.NUMBER).description("상품가격"),
            fieldWithPath("productStatus").description(JsonFieldType.OBJECT).description("상품상태"),
            fieldWithPath("productCategory").description(JsonFieldType.OBJECT).description("상품 카테고리"),
            fieldWithPath("productEnum").description(JsonFieldType.OBJECT).description("상품 거래 상태")
        )));


  }

  @Test
  @WithCustomMockUser
  void getProducts() throws Exception {
    PageDto pageDto = PageDto.builder().page(1).size(10).isAsc(false).sortBy("createdAt").build();
    Pageable pageable = pageDto.toPageable();
    ProductResponseDto productResponseDto = ProductResponseDto.builder()
        .productId(1L)
        .productName("productName")
        .productPrice(1000)
        .productStatus(ProductStatus.S)
        .productCategory(ProductCategory.CAR)
        .productEnum(ProductEnum.나눔)
        .build();

    PagingProductResponse pagingProductResponse = PagingProductResponse.builder()
        .productName("상품이름").productPrice(1000L).build();

    Page<PagingProductResponse> productResponseDtos = new PageImpl<>(
        Collections.singletonList(pagingProductResponse), pageable, 1);

    given(productService.viewAllProduct(pageDto)).willReturn(productResponseDtos);

    ResultActions resultActions = mockMvc.perform(get("/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(pageDto))
            .with(csrf()))
        .andExpect(status().isOk());
    resultActions.andDo(document("productController/getProducts",
        getDocumentRequest(),
        getDocumentResponse(),
        requestFields(
            fieldWithPath("page").type(JsonFieldType.NUMBER).description("페이지"),
            fieldWithPath("size").type(JsonFieldType.NUMBER).description("글의 갯수"),
            fieldWithPath("sortBy").type(JsonFieldType.STRING).description("정렬 기준"),
            fieldWithPath("asc").type(JsonFieldType.BOOLEAN).description("올림/내림차순"))
//        responseFields(
//            fieldWithPath("productName").type(JsonFieldType.STRING).description("상품이름"),
//            fieldWithPath("productPrice").type(JsonFieldType.NUMBER).description("상품가격")
//        )
    ));

  }

  @Test
  @WithCustomMockUser
  void update() throws Exception {
    Long productId = 1L;
    ProductRequestDto productRequestDto = ProductRequestDto.builder()
        .productName("productName")
        .productPrice(1000)
        .productStatus(ProductStatus.S)
        .productCategory(ProductCategory.CAR)
        .productEnum(ProductEnum.나눔)
        .build();
    StatusResponse statusResponse = StatusResponse.valueOf(ResponseMessages.SUCCESS);

    ResultActions resultActions = mockMvc.perform(
            put("/products/update/{productId}", productId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(productRequestDto))
                .with(csrf()))
        .andExpect(status().isOk());

    resultActions.andDo(document("productController/update",
        getDocumentRequest(),
        getDocumentResponse(),
        requestFields(
            fieldWithPath("productName").description(JsonFieldType.STRING).description("상품명"),
            fieldWithPath("productPrice").description(JsonFieldType.NUMBER).description("상품가격"),
            fieldWithPath("productStatus").description(JsonFieldType.OBJECT).description("상품상태"),
            fieldWithPath("productCategory").description(JsonFieldType.OBJECT)
                .description("상품 카테고리"),
            fieldWithPath("productEnum").description(JsonFieldType.OBJECT).description("거래 상태")
                .description("상품 카테고리"),
            fieldWithPath("productEnum").description(JsonFieldType.OBJECT).description("상품 거래 상태")
        ),
        responseFields(
            fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("상태 반환 코드"),
            fieldWithPath("message").type(JsonFieldType.STRING).description("상태 메시지")
        )
    ));

  }

  @Test
  @WithCustomMockUser
  void deleteProduct() throws Exception {
    Long productId = 1L;
    ResultActions resultActions = mockMvc.perform(
            delete("/products/{productId}", productId).with(csrf()))
        .andExpect(status().isNoContent());
    StatusResponse statusResponse = StatusResponse.valueOf(ResponseMessages.DELETE_SUCCESS);
    resultActions.andDo(document("productController/deleteProduct",
        getDocumentResponse(),
        responseFields(
            fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("상태 반환 코드"),
            fieldWithPath("message").type(JsonFieldType.STRING).description("상태 메시지")
        )
    ));
  }
}
