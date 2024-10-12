package ru.clevertec.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.clevertec.validation.ChannelValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ChannelValidator.class)
public @interface UniqueChannelTitle {

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}