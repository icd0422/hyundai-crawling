package com.test.hyundaicrawling.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@ControllerAdvice
public class ApplicationExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {ApplicationException.class})
    ResponseEntity<Object> handleApplicationException(ApplicationException ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorCode(ex.getErrorCode());
        errorMessage.setReason(ex.getReason());

        log.warn(ex.getMessage(), ex);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
