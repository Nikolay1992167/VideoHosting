package ru.clevertec.exception.handler;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record IncorrectData(LocalDateTime timestamp,
                            String errorMessage,
                            int errorStatus) {
}