package com.example.townmarket.common.exception;

import com.example.townmarket.common.dto.StatusResponse;
import com.example.townmarket.common.enums.ResponseMessages;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;


@Service
public class ExceptionService {

  public StatusResponse getErrorResponse(ResponseMessages messages) {
    return StatusResponse.valueOf(messages);
  }

  public StatusResponse makeMethodArgumentNotValidException(BindingResult bindingResult,
      String getMessage) {
    //에러가 있다면
    if (bindingResult.hasErrors()) {
      //DTO에 유효성체크를 걸어놓은 어노테이션명을 가져온다.
      String bindResultCode = Objects.requireNonNull(bindingResult.getFieldError()).getCode();
      //유형지정
      switch (Objects.requireNonNull(bindResultCode)) {
        case "Pattern" -> {
          return new StatusResponse(HttpStatus.BAD_REQUEST.value(), getMessage);
        }
        case "Size" -> {
          return new StatusResponse(HttpStatus.BAD_REQUEST.value(), "문자열의 길이가 지정된 길이와 다릅니다");
        }
      }
    }
    return new StatusResponse(HttpStatus.BAD_REQUEST.value(), getMessage);
  }

}
