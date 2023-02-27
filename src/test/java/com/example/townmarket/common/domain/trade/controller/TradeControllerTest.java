package com.example.townmarket.common.domain.trade.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@WebMvcTest(controllers = TradeController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class TradeControllerTest {

  @Test
  void getPurchaseList() {
  }

  @Test
  void getSalesList() {
  }

  @Test
  void createTrade() {
  }
}
