package com.example.comit.global.exception.handler;

import com.example.comit.domain.auth.exception.PasswordWrongException;
import com.example.comit.domain.auth.exception.UserAlreadyExistException;
import com.example.comit.domain.auth.exception.UserNotFoundException;
import com.example.comit.global.exception.ErrorResponse;
import com.example.comit.global.exception.exceptionCollection.TokenExpirationException;
import com.example.comit.global.exception.exceptionCollection.TokenNotValidException;
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

    @ExceptionHandler(TokenExpirationException.class)
    public ResponseEntity<ErrorResponse> handleUnknownException(HttpServletRequest request, TokenExpirationException ex){
        printError(request, ex, ex.getErrorCode().getMessage());
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode().getMessage(), ex.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    @ExceptionHandler(TokenNotValidException.class)
    public ResponseEntity<ErrorResponse> handleUnknownException(HttpServletRequest request, TokenNotValidException ex){
        printError(request, ex, ex.getErrorCode().getMessage());
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode().getMessage(), ex.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleUnknownException(HttpServletRequest request, UserAlreadyExistException ex){
        printError(request, ex, ex.getErrorCode().getMessage());
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode().getMessage(), ex.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUnknownException(HttpServletRequest request, UserNotFoundException ex){
        printError(request, ex, ex.getErrorCode().getMessage());
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode().getMessage(), ex.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    @ExceptionHandler(PasswordWrongException.class)
    public ResponseEntity<ErrorResponse> handleUnknownException(HttpServletRequest request, PasswordWrongException ex){
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
