package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.dto.user.UserRequest;
import ru.clevertec.dto.user.UserResponse;
import ru.clevertec.entity.User;

@Mapper
public interface UserMapper {

    User fromRequest(UserRequest request);

    User fromRequest(Long id, UserRequest request);

    UserResponse toResponse(User user);
}