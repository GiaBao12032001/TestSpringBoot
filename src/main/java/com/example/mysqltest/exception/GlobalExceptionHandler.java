package com.example.mysqltest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> applicationExceptionHandler(ApplicationException e) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ErrorResponse("406:NOT_ACCEPTABLE", e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ErrorResponse("406:NOT_ACCEPTABLE",
                "You may be using a wrong variable or using a" +
                        "wrong request type. Please change to continue. If you are trying to add something use POST." +
                        "Replacing or updating something use PUT. Show something use GET. Delete something use Delete." +
                        "For variables, id, age, salaries, use numbers. Others, please use letters."));
    }
}
