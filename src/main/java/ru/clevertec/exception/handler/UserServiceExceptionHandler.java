package ru.clevertec.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.clevertec.exception.MultipartGetBytesException;
import ru.clevertec.exception.NotFoundException;
import ru.clevertec.exception.ServiceException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class UserServiceExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<IncorrectData> exception(ServiceException exception) {
        return getResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<IncorrectData> notFoundException(NotFoundException exception) {
        return getResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MultipartGetBytesException.class)
    public ResponseEntity<IncorrectData> multipartGetBytesException(MultipartGetBytesException exception) {
        return getResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<IncorrectData> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new LinkedHashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return getResponse(errors.toString(), HttpStatus.BAD_REQUEST);
    }

    private static ResponseEntity<IncorrectData> getResponse(String message, HttpStatus status) {
        IncorrectData incorrectData = new IncorrectData(LocalDateTime.now(), message, status.value());
        return ResponseEntity.status(status).body(incorrectData);
    }
}