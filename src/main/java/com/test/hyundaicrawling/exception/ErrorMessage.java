package com.test.hyundaicrawling.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage {

    private String errorCode;

    private String reason;
}
