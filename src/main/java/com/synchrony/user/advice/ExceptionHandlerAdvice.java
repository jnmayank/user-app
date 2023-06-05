package com.synchrony.user.advice;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

//@RestControllerAdvice
public class ExceptionHandlerAdvice extends ExceptionHandlerExceptionResolver {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    void handleAuthenticationException(Exception ex) {
        // to do
    }
}
