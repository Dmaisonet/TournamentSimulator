package com.example.tournamentSimulator.error;

public class ParticipantNameAlreadyExistException extends Exception {

    public ParticipantNameAlreadyExistException() {
        super();
    }

    public ParticipantNameAlreadyExistException(String message) {
        super(message);
    }

    public ParticipantNameAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParticipantNameAlreadyExistException(Throwable cause) {
        super(cause);
    }

    protected ParticipantNameAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
