package com.raj.travels.udan.connection_service.advice;

import com.raj.travels.commons.dto.connection.ConnectionResponse;
import com.raj.travels.udan.connection_service.exceptions.ConnectionFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.raj.travels.udan.connection_service.constants.MessageConstant.CONNECTION_ERROR;
import static com.raj.travels.udan.connection_service.constants.MessageConstant.CONNECTION_FAILED;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ConnectionResponse> handleAllExceptions(Exception ex) {
        log.error("While processing request, an error occurred: {}", ex.getMessage(), ex);
        ConnectionResponse errorResponse = ConnectionResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(CONNECTION_ERROR)
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConnectionFailedException.class)
    public ResponseEntity<ConnectionResponse> handleConnectionFailedException(ConnectionFailedException ex) {
        log.error("Connection failed: {}", ex.getMessage(), ex);
        ConnectionResponse errorResponse = ConnectionResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(CONNECTION_FAILED)
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
