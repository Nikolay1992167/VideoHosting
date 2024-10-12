package ru.clevertec.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import ru.clevertec.repository.ChannelRepository;
import ru.clevertec.validation.annotation.UniqueChannelTitle;

@RequiredArgsConstructor
public class ChannelValidator implements ConstraintValidator<UniqueChannelTitle, String> {

    private final ChannelRepository channelRepository;

    @Override
    public void initialize(UniqueChannelTitle constraintAnnotation) {
    }

    @Override
    public boolean isValid(String title, ConstraintValidatorContext context) {
        return title != null && !channelRepository.existsByTitle(title);
    }
}