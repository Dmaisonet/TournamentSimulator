package com.example.tournamentSimulator.error;

import com.example.tournamentSimulator.entity.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice //I can throw custom exceptions for any class
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ParticipantNameAlreadyExistException.class)
    public ResponseEntity<ErrorMessage> participantNameAlreadyExistException(ParticipantNameAlreadyExistException exception,
                                                                             WebRequest request) {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(message);
    }

    @ExceptionHandler(ParticipantDoesNotExistException.class)
    public ResponseEntity<ErrorMessage> participantDoesNotExistException(ParticipantDoesNotExistException exception,
                                                                         WebRequest request) {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(message);

    }
}
