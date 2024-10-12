package ru.clevertec.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.clevertec.validation.UserValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserValidator.class)
public @interface UniqueUserFields {

    String message() default "User nickname or email must be unique!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}