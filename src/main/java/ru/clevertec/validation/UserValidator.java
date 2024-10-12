package ru.clevertec.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import ru.clevertec.dto.user.UserRequest;
import ru.clevertec.repository.UserRepository;
import ru.clevertec.validation.annotation.UniqueUserFields;

@RequiredArgsConstructor
public class UserValidator implements ConstraintValidator<UniqueUserFields, UserRequest> {

    private final UserRepository userRepository;

    @Override
    public void initialize(UniqueUserFields constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserRequest request, ConstraintValidatorContext context) {
        if (request == null) {
            return true;
        }

        boolean nicknameExists = userRepository.findByNickname(request.nickname()).isPresent();
        boolean emailExists = userRepository.findByEmail(request.email()).isPresent();

        if (nicknameExists || emailExists) {
            context.disableDefaultConstraintViolation();
            if (nicknameExists) {
                context.buildConstraintViolationWithTemplate("Nickname must be unique!")
                        .addPropertyNode("nickname")
                        .addConstraintViolation();
            }
            if (emailExists) {
                context.buildConstraintViolationWithTemplate("Email must be unique!")
                        .addPropertyNode("email")
                        .addConstraintViolation();
            }
            return false;
        }

        return true;
    }
}