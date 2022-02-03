package com.project.exception;

import com.project.model.response.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.Optional;

@ControllerAdvice
public class AppExceptionsHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(new Date(), Optional.ofNullable(ex.getLocalizedMessage()).orElse(ex.toString()));
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handling a specific exception
    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<Object> handleNullPointerException(Exception ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(new Date(), Optional.ofNullable(ex.getLocalizedMessage()).orElse(ex.toString()));
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*// Handling multiple exception
    @ExceptionHandler(value = {NullPointerException.class, UserServiceException.class})
    public ResponseEntity<Object> handleNullPointerException(Exception ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(new Date(), Optional.ofNullable(ex.getLocalizedMessage()).orElse(ex.toString()));
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }*/

    // Handling a custom exception
    @ExceptionHandler(value = {UserServiceException.class})
    public ResponseEntity<Object> handleUserServiceException(UserServiceException ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(new Date(), Optional.ofNullable(ex.getLocalizedMessage()).orElse(ex.toString()));
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
