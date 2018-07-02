package be.avidoo.sandbox.springboot2sandbox.controller.aspect;

import be.avidoo.sandbox.springboot2sandbox.exceptions.NotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorAdvice extends AbstractAdvice {

    @ExceptionHandler(NotFoundException.class)
    public HttpEntity objectNotFoundException(final NotFoundException ex) {
        return getHttpEntity(NotFoundException.ERROR_CODE, ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
