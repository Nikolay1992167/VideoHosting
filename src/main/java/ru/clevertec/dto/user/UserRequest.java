package ru.clevertec.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import ru.clevertec.validation.annotation.UniqueUserFields;

@UniqueUserFields
public record UserRequest(@NotBlank
                          @Size(max = 50)
                          String nickname,

                          @NotBlank
                          @Size(max = 50)
                          String name,

                          @Email
                          @Size(max = 100)
                          String email) {
}