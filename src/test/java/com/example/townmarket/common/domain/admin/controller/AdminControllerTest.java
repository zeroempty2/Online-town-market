package com.example.townmarket.common.domain.admin.controller;

import static org.mockito.Mockito.mock;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.townmarket.admin.controller.AdminController;
import com.example.townmarket.admin.service.AdminServiceImpl;
import com.example.townmarket.common.dto.PageDto;
import com.example.townmarket.common.globalException.ExceptionController;
import com.example.townmarket.common.util.SetHttpHeaders;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@WebMvcTest(controllers = AdminController.class)
@MockBean(JpaMetamodelMappingContext.class)
class AdminControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;
  @MockBean
  private ExceptionController exceptionController;
  @MockBean
  private AdminServiceImpl adminService;
  @MockBean
  private SetHttpHeaders httpHeaders;

  @Test
  @DisplayName("유저 목록 조회")
  @WithMockUser(username = "test", roles = "TOP_MANAGER")
  void viewAllUser() throws Exception {
    //given
    PageDto pageDto = mock(PageDto.class);
//    var headers = mock(HttpHeaders.class);
//    when(adminService.viewAllUser(pageDto)).thenReturn(Page.empty());
//    when(httpHeaders.setHeaderTypeJson()).thenReturn(headers);

    //when
    ResultActions resultActions = mockMvc.perform(get("/admin/users")
        .contentType(MediaType.APPLICATION_JSON)
        .content(String.valueOf(pageDto))
        .with(csrf()));

    //then
    resultActions.andExpect(status().isOk());
  }
}
