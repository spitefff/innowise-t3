package org.putkov.tt.exception;

public class AuthoserviceException extends Exception{
    public AuthoserviceException(Throwable cause) {
        super(cause);
    }

    public AuthoserviceException(String message) {
        super(message);
    }

    public AuthoserviceException(String message, Throwable cause) {
        super(message, cause);
    }
}
