package com.cocktails.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RecipeRestExceptionHandler {

    // Add an exception handler for not found recipes
    @ExceptionHandler
    public ResponseEntity<RecipeErrorResponse> handleException(RecipeNotFoundException exc) {

        // create a RecipeErrorResponse

        RecipeErrorResponse error = new RecipeErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Add exception handler to catch any exception

    @ExceptionHandler
    public ResponseEntity<RecipeErrorResponse> handleException(Exception exc) {

        // create RecipeErrorResponse
        RecipeErrorResponse error = new RecipeErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
