package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.dto.channel.ChannelNamesResponse;
import ru.clevertec.dto.user.UserRequest;
import ru.clevertec.dto.user.UserResponse;
import ru.clevertec.entity.Channel;
import ru.clevertec.entity.Subscriber;
import ru.clevertec.entity.User;
import ru.clevertec.exception.NotFoundException;
import ru.clevertec.exception.ServiceException;
import ru.clevertec.mapper.UserMapper;
import ru.clevertec.repository.UserRepository;
import ru.clevertec.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserResponse save(UserRequest request) {
        return Optional.of(request)
                .map(userMapper::fromRequest)
                .map(userRepository::save)
                .map(userMapper::toResponse)
                .orElseThrow(() -> new ServiceException("Error to save user"));
    }

    @Override
    @Transactional
    public UserResponse updateById(Long id, UserRequest request) {
        return userRepository.findById(id)
                .map(user -> userMapper.fromRequest(user.getId(), request))
                .map(userRepository::save)
                .map(userMapper::toResponse)
                .orElseThrow(()-> NotFoundException.of(User.class, id));
    }

    @Override
    public ChannelNamesResponse findAllSubscriberChannelNamesById(Long id) {
        return new ChannelNamesResponse(userRepository.findWithSubscriptionsChannelById(id)
                .orElseThrow(()-> NotFoundException.of(User.class, id))
                .getSubscriptions()
                .stream()
                .map(Subscriber::getChannel)
                .map(Channel::getTitle)
                .toList());
    }
}