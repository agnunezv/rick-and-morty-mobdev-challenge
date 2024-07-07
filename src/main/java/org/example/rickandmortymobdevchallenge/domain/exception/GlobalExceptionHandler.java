package org.example.rickandmortymobdevchallenge.domain.exception;

import org.example.rickandmortymobdevchallenge.domain.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WebClientResponseException.NotFound.class)
    public final ResponseEntity<ErrorMessage> handleNotFoundException(WebClientResponseException.NotFound ex) {
        ErrorMessage message = new ErrorMessage()
                .setStatus(HttpStatus.NOT_FOUND.toString())
                .setMessage(ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorMessage> handleAllExceptions(Exception ex) {
        ErrorMessage message = new ErrorMessage()
                .setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .setMessage(ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
