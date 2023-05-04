package com.pro.weatherservice.exception;

import com.pro.weatherservice.dto.ErrorMessageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        ErrorMessageDto errorMessage = new ErrorMessageDto(ex.getMessage(), 404);
        return ResponseEntity.internalServerError().body(errorMessage);
    }
}
