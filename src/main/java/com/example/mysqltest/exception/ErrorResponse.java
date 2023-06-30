package com.example.mysqltest.exception;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class ErrorResponse {
    private final String code;
    private final String message;
}
