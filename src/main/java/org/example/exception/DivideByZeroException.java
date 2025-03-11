package org.example.exception;

public class DivideByZeroException extends RuntimeException {

    public DivideByZeroException() {
        super("Divide by zero");
    }
}
