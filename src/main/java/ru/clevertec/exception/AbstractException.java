package ru.clevertec.exception;

public abstract class AbstractException extends RuntimeException {

    public AbstractException(String message) {
        super(message);
    }
}