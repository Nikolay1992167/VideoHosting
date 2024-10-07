package ru.clevertec.exception;

public class NotFoundException extends AbstractException {

    public NotFoundException(String message) {
        super(message);
    }

    public static NotFoundException of(Class<?> clazz, Object field) {
        return new NotFoundException(clazz.getSimpleName() + " with " + field + " not found!");
    }
}