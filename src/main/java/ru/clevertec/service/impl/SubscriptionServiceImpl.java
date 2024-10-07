package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.dto.subscriber.SubscriberResponse;
import ru.clevertec.enam.SubscriptionStatus;
import ru.clevertec.entity.Channel;
import ru.clevertec.entity.Subscriber;
import ru.clevertec.entity.User;
import ru.clevertec.exception.NotFoundException;
import ru.clevertec.mapper.SubscriberMapper;
import ru.clevertec.repository.ChannelRepository;
import ru.clevertec.repository.SubscriptionRepository;
import ru.clevertec.repository.UserRepository;
import ru.clevertec.service.SubscriptionService;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final ChannelRepository channelRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final SubscriberMapper subscriptionMapper;

    @Override
    @Transactional
    public SubscriberResponse subscribeOn(Long userId, Long channelId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> NotFoundException.of(User.class, userId));
        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> NotFoundException.of(Channel.class, channelId));
        Subscriber subscriber = subscriptionRepository.save(subscriptionMapper.toSubscription(user, channel));
        return subscriptionMapper.toResponse(subscriber, SubscriptionStatus.ENABLED);
    }

    @Override
    @Transactional
    public SubscriberResponse subscribeOff(Long userId, Long channelId) {
        return subscriptionRepository.findByUserIdAndChannelId(userId, channelId)
                .map(subscription -> {
                    subscriptionRepository.delete(subscription);
                    return subscription;
                })
                .map(subscription -> subscriptionMapper.toResponse(subscription, SubscriptionStatus.DISABLED))
                .orElseThrow(() -> new NotFoundException("Subscription with user_id %s and channel_id %s is not found"
                        .formatted(userId, channelId)));
    }
}