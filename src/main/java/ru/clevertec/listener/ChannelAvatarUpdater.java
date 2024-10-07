package ru.clevertec.listener;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.entity.Channel;
import ru.clevertec.repository.ChannelRepository;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class ChannelAvatarUpdater {

    private final ChannelRepository channelRepository;

    @SneakyThrows
    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void updateAvatarsIfNeeded() {
        List<Channel> channels = channelRepository.findAll();
        String title = "spunch_bob";
        byte[] avatarBytes = Files.readAllBytes(Paths.get("src/main/resources/avatars/%s%s".formatted(title, ".jpg")));

        for (Channel channel : channels) {
            if (Objects.isNull(channel.getAvatar())) {
                channelRepository.updateAvatarById(channel.getId(), avatarBytes);
            }
        }
    }
}