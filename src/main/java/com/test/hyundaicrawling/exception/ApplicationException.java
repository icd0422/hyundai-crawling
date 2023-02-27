package com.test.hyundaicrawling.exception;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

    private String errorCode;

    private String reason;

    public ApplicationException(String errorCode, String reason) {
        super("errorCode: " + errorCode + ",  reason:" + reason);
        this.errorCode = errorCode;
        this.reason = reason;
    }

    public ApplicationException(String errorCode, String reason, Throwable cause) {
        super("errorCode: " + errorCode + ",  reason:" + reason, cause);
        this.errorCode = errorCode;
        this.reason = reason;
    }

    public static class FetchHtmlTimeoutException extends ApplicationException {
        public FetchHtmlTimeoutException() {
            super("FETCH_HTML_TIME_OUT", "Html 조회 시간이 초과되었습니다.");
        }
    }
}
