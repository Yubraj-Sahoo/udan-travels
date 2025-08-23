package com.raj.travels.udan.connection_service.controller.advice;

import com.raj.travels.udan.connection_service.exception.AmadeusConnectionException;
import com.raj.travels.udan.connection_service.exception.UnableToGetConnectionException;
import com.raj.travels.udan.travel_domain.dto.connection.AuthenticationErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AmadeusConnectionException.class)
    public ResponseEntity<AuthenticationErrorResponse> handleAmadeusException(AmadeusConnectionException ex,
                                                                              HttpServletRequest request) {
        log.error("AmadeusConnectionException occurred", ex);

        AuthenticationErrorResponse error = AuthenticationErrorResponse.builder()
                .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .errorMessage("Amadeus Connection Error")
                .build();

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<AuthenticationErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex,
                                                                                      HttpServletRequest request) {
        log.error("IllegalArgumentException occurred", ex);

        AuthenticationErrorResponse error = AuthenticationErrorResponse.builder()
                .status(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .errorMessage(ex.getMessage()) // better than hardcoded
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnableToGetConnectionException.class)
    public ResponseEntity<AuthenticationErrorResponse> handleUnableToGetConnectionException(UnableToGetConnectionException ex,
                                                                                            HttpServletRequest request) {
        log.error("UnableToGetConnectionException occurred", ex);

        AuthenticationErrorResponse error = AuthenticationErrorResponse.builder()
                .status(String.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()))
                .errorMessage("Unable to get connection for the specified connection type")
                .build();

        return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
    }

    // optional catch-all fallback
    @ExceptionHandler(Exception.class)
    public ResponseEntity<AuthenticationErrorResponse> handleGenericException(Exception ex,
                                                                              HttpServletRequest request) {
        log.error("Unexpected exception occurred", ex);

        AuthenticationErrorResponse error = AuthenticationErrorResponse.builder()
                .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .errorMessage("Unexpected error occurred")
                .build();

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
