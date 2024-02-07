package com.ldms.customer.exception.handler;

import com.ldms.customer.exception.CustomerNotFoundException;
import com.ldms.customer.exception.InvalidCustomerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handleCustomerNotFoundException(CustomerNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCustomerException.class)
    public ResponseEntity<String> handleCustomerValidationException(InvalidCustomerException ex) {
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>("Validation Error: " + errorMessage, HttpStatus.BAD_REQUEST);
    }

    // Add more exception handlers as needed...

    // Generic exception handler for unhandled exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
