package com.example.comit.global.exception.handler;

import com.example.comit.global.exception.ErrorResponse;
import com.example.comit.global.exception.exceptionCollection.UnknownException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(UnknownException.class)
    public ResponseEntity<ErrorResponse> handleUnknownException(HttpServletRequest request, UnknownException ex){
        printError(request, ex, ex.getErrorCode().getMessage());
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode().getMessage(), ex.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    private void printError(HttpServletRequest request, RuntimeException ex, String message) {
        log.error(request.getRequestURI());
        log.error(message);
        ex.printStackTrace();
    }

}
