package ru.clevertec.service;


import ru.clevertec.dto.subscriber.SubscriberResponse;

public interface SubscriptionService {

    SubscriberResponse subscribeOn(Long userId, Long channelId);

    SubscriberResponse subscribeOff(Long userId, Long channelId);
}