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

    public static class FetchHtmlMalformedURLException extends ApplicationException {
        public FetchHtmlMalformedURLException() {
            super("FETCH_HTML_MALFORMED_URL", "지원하지 않는 url 형식입니다.");
        }
    }
}
