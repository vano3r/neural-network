package org.example.matrix.exception;

public class NumberOfColumnsEqual extends RuntimeException {
    public NumberOfColumnsEqual() {
    }

    public NumberOfColumnsEqual(String message) {
        super(message);
    }

    public NumberOfColumnsEqual(String message, Throwable cause) {
        super(message, cause);
    }
}
