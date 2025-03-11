package org.example.service;

public class NotSupportOperatorException extends RuntimeException {

    public NotSupportOperatorException() {
        super("Operator not supported");
    }
}
