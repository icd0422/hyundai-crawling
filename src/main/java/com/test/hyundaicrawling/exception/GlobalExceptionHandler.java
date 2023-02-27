package com.test.hyundaicrawling.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@Order(Ordered.LOWEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleException(Exception ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorCode("INTERNAL_SERVER_ERROR");
        errorMessage.setReason("서버 확인중입니다.");

        log.warn(ex.getMessage(), ex);
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
