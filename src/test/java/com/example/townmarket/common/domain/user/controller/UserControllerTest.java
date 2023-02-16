package com.example.townmarket.common.domain.user.controller;

import static com.example.townmarket.restdocs.ApiDocumentUtils.getDocumentRequest;
import static com.example.townmarket.restdocs.ApiDocumentUtils.getDocumentResponse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.townmarket.common.domain.user.dto.LoginRequestDto;
import com.example.townmarket.common.domain.user.dto.SignupRequestDto;
import com.example.townmarket.common.domain.user.service.UserService;
import com.example.townmarket.common.dto.StatusResponse;
import com.example.townmarket.common.enums.ResponseMessages;
import com.example.townmarket.common.util.SetHttpHeaders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@WebMvcTest(controllers = UserController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class UserControllerTest {

  @Autowired
  MockMvc mockMvc;
  @Autowired
  ObjectMapper objectMapper;

  @MockBean
  UserService userService;

  @MockBean
  SetHttpHeaders setHttpHeaders;

  @Test
  @DisplayName("회원가입 성공")
  @WithMockUser
  void signup() throws Exception {
    SignupRequestDto signupRequestDto = SignupRequestDto.builder()
        .username("username")
        .password("Password")
        .email("xxx0011@gmail.com")
        .nickname("nickname")
        .region("서울")
        .phoneNumber("010-1111-2222")
        .build();

    StatusResponse statusResponse = StatusResponse.valueOf(ResponseMessages.CREATED_SUCCESS);

    ResultActions resultActions = mockMvc.perform(post("/users/signup")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsBytes(signupRequestDto))
        .with(csrf()));

    resultActions.andExpect(status().isOk())
        .andDo(document("usercontroller/signup",
            getDocumentRequest(),
            getDocumentResponse(),
            requestFields(
                fieldWithPath("username").type(JsonFieldType.STRING).description("유저 이름"),
                fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
                fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                fieldWithPath("nickname").type(JsonFieldType.STRING).description("닉네임"),
                fieldWithPath("region").type(JsonFieldType.STRING).description("지역"),
                fieldWithPath("phoneNumber").type(JsonFieldType.STRING).description("핸드폰 번호")
            ),
            responseFields(
                fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("상태 반환 코드"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("상태 메시지")
            )
        ));

  }

  @Test
  @WithMockUser
  void login() throws Exception {
    LoginRequestDto loginRequestDto = LoginRequestDto.builder()
        .username("username")
        .password("Xxxx11234")
        .build();

    StatusResponse statusResponse = StatusResponse.valueOf(ResponseMessages.SUCCESS);

    ResultActions resultActions = mockMvc.perform(
        post("/users/login").contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(loginRequestDto))
            .with(csrf())).andExpect(status().isOk());

    resultActions.andExpect(status().isOk())
        .andDo(document("usercontroller/login",
            getDocumentRequest(),
            getDocumentResponse(),
            requestFields(
                fieldWithPath("username").type(JsonFieldType.STRING).description("유저 이름"),
                fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호")
            ),
            responseFields(
                fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("상태 반환 코드"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("상태 메시지")
            )
        ));


  }

  @Test
  void logout() {
  }

  @Test
  void updateUser() {
  }

  @Test
  void updateRegion() {
  }

  @Test
  void deleteUser() {
  }

  @Test
  void updateProfile() {
  }

  @Test
  void showProfile() {
  }
}
