package org.example.exception;

public class NotSupportOperatorException extends RuntimeException {

    public NotSupportOperatorException() {
        super("Operator not supported");
    }
}
