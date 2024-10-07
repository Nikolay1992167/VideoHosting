package ru.clevertec.service;

import ru.clevertec.dto.channel.ChannelNamesResponse;
import ru.clevertec.dto.user.UserRequest;
import ru.clevertec.dto.user.UserResponse;

public interface UserService {

    UserResponse save(UserRequest request);

    UserResponse updateById(Long id, UserRequest request);

    ChannelNamesResponse findAllSubscriberChannelNamesById(Long id);
}