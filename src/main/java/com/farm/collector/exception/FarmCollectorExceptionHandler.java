package com.farm.collector.exception;

import com.farm.collector.dto.response.FarmCollectorErrorRes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class FarmCollectorExceptionHandler {

    /**
     * Handles exceptions of type IllegalArgumentException thrown in the application.
     * This method is annotated with @ExceptionHandler, which allows it to intercept
     * exceptions thrown by controllers and return a custom response.
     *
     * @param ex the exception that was thrown, containing details about the error
     * @param request the current request, which can be used for additional context (if needed)
     * @return a ResponseEntity containing a FarmCollectorErrorRes object with error details and a BAD_REQUEST status
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<FarmCollectorErrorRes> handleCustomExceptions(Exception ex, WebRequest request) {
        FarmCollectorErrorRes errorResponse = new FarmCollectorErrorRes(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
