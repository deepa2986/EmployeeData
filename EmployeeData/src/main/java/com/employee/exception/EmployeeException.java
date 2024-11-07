package com.employee.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class EmployeeException extends RuntimeException{

    private final String errorMessage;
    private final HttpStatus httpStatus;
}
