package com.example.tournamentSimulator.error;

public class ParticipantDoesNotExistException extends Exception {

    public ParticipantDoesNotExistException() {
        super();
    }

    public ParticipantDoesNotExistException(String message) {
        super(message);
    }

    public ParticipantDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParticipantDoesNotExistException(Throwable cause) {
        super(cause);
    }

    protected ParticipantDoesNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
