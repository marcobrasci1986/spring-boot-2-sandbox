package be.avidoo.sandbox.springboot2sandbox.controller.aspect;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class AbstractAdvice {

    public static final String EXCEPTION_CODE_HEADER_NAME = "zop-exception-code";
    public static final String EXCEPTION_MESSAGE_HEADER_NAME = "zop-exception-message";

    AbstractAdvice() {
    }

    HttpEntity getHttpEntity(final String code, final String message, final HttpStatus httpStatus) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(EXCEPTION_CODE_HEADER_NAME, code);
        httpHeaders.add(EXCEPTION_MESSAGE_HEADER_NAME, message);
        return new ResponseEntity(httpHeaders, httpStatus);
    }
}
