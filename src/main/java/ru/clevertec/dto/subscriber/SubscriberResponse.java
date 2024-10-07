package ru.clevertec.dto.subscriber;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import ru.clevertec.enam.SubscriptionStatus;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record SubscriberResponse(String userNickname,
                                 String channelTitle,
                                 SubscriptionStatus subscriptionStatus) {
}