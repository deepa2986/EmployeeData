package com.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.util.ReflectionUtils.getField;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<String> employeeException(EmployeeException exception){
        return new ResponseEntity<>(exception.getErrorMessage(),exception.getHttpStatus());
    }

    public ResponseEntity<Map<String,String>> handleInvalidMethodArgumentException(MethodArgumentNotValidException exception){
        Map<String,String> response = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error -> {
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            response.put(fieldName,message);
        }));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
