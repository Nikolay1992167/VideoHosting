package ru.clevertec.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.entity.Subscriber;
import ru.clevertec.entity.SubscriberId;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscriber, SubscriberId> {

    @EntityGraph(attributePaths = {"user", "channel"})
    Optional<Subscriber> findByUserIdAndChannelId(Long userId, Long channelId);
}