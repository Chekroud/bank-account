package org.bank.bankaccount.application.controller;

import lombok.extern.slf4j.Slf4j;
import org.bank.bankaccount.application.response.Error;
import org.bank.bankaccount.domain.exception.GenericException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(GenericException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<Error> handleGenericError(GenericException e) {
        log.error("An error has occurred, {}", String.valueOf(e));
        return new ResponseEntity<>(new Error(e.getMessage(), e.getCode()), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<Error> handleAccessDeniedException(final AccessDeniedException e) {
        log.error("Error, access denied {}", String.valueOf(e));
        return new ResponseEntity<>(new Error("No access for this resource."), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Error> handleMethodArgumentNotValidError(MethodArgumentNotValidException e) {
        log.error("Error, One or more arguments are invalid, {}", String.valueOf(e));
        return new ResponseEntity<>(new Error("One or more arguments are invalid."), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Error> handleExceptionError(Exception e) {
        log.error("An error has occurred, {}", String.valueOf(e));
        return new ResponseEntity<>(new Error("An error has happened during application run."), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
