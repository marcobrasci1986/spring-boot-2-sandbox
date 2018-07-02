package be.avidoo.sandbox.springboot2sandbox.controller.aspect;

import be.avidoo.sandbox.springboot2sandbox.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ErrorAdvice extends AbstractAdvice {

    @ExceptionHandler(NotFoundException.class)
    public HttpEntity objectNotFoundException(final NotFoundException ex) {
        log.info("::NotFoundException::");
        return getHttpEntity(NotFoundException.ERROR_CODE, ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
