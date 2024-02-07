package com.ldms.customer.exception.handler;

import com.ldms.customer.exception.CustomerNotFoundException;
import com.ldms.customer.exception.InvalidCustomerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.ldms.customer.constant.CustomerConstants.UNEXPECTED_ERROR;
import static com.ldms.customer.constant.CustomerConstants.VALIDATION_ERROR;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handleCustomerNotFoundException(CustomerNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCustomerException.class)
    public ResponseEntity<String> handleCustomerValidationException(InvalidCustomerException ex) {
        return new ResponseEntity<>(VALIDATION_ERROR + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>(UNEXPECTED_ERROR + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
