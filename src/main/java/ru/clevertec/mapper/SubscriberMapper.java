package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.dto.subscriber.SubscriberResponse;
import ru.clevertec.enam.SubscriptionStatus;
import ru.clevertec.entity.Channel;
import ru.clevertec.entity.Subscriber;
import ru.clevertec.entity.User;

@Mapper
public interface SubscriberMapper {

    Subscriber toSubscription(User user, Channel channel);

    @Mapping(target = "userNickname", source = "subscriber.user.nickname")
    @Mapping(target = "channelTitle", source = "subscriber.channel.title")
    SubscriberResponse toResponse(Subscriber subscriber, SubscriptionStatus subscriptionStatus);
}