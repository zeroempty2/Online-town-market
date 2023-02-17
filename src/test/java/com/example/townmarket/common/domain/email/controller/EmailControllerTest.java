package com.example.townmarket.common.domain.email.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.example.townmarket.annotation.WithCustomMockUser;
import com.example.townmarket.common.domain.comment.controller.CommentController;
import com.example.townmarket.common.domain.comment.service.CommentService;
import com.example.townmarket.common.domain.email.service.EmailService;
import com.example.townmarket.common.util.SetHttpHeaders;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@WebMvcTest(controllers = EmailController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class EmailControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @MockBean
  SetHttpHeaders setHttpHeaders;

  @MockBean
  EmailService emailService;

  @Test
  @WithCustomMockUser
  void emailConfirm() {

  }

  @Test
  @WithCustomMockUser
  void verifyCode() {
  }
}
