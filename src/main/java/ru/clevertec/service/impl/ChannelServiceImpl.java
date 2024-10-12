package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.clevertec.dto.channel.ChannelDetailedInformationResponse;
import ru.clevertec.dto.channel.ChannelFilterResponse;
import ru.clevertec.dto.channel.ChannelRequest;
import ru.clevertec.dto.channel.ChannelResponse;
import ru.clevertec.enam.Language;
import ru.clevertec.entity.Channel;
import ru.clevertec.entity.User;
import ru.clevertec.exception.MultipartGetBytesException;
import ru.clevertec.exception.NotFoundException;
import ru.clevertec.mapper.ChannelMapper;
import ru.clevertec.repository.ChannelRepository;
import ru.clevertec.repository.UserRepository;
import ru.clevertec.service.ChannelService;

import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChannelServiceImpl implements ChannelService {

    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;
    private final ChannelMapper channelMapper;

    @Override
    @Transactional
    public ChannelResponse saveByAuthorId(Long authorId, ChannelRequest request, MultipartFile file) {
        return userRepository.findById(authorId)
                .map(user -> channelMapper.fromRequest(user.getId(), request, getAvatar(file)))
                .map(channelRepository::save)
                .map(channelMapper::toResponse)
                .orElseThrow(() -> NotFoundException.of(User.class, authorId));
    }

    @Override
    @Transactional
    public ChannelResponse updateById(Long id, ChannelRequest request, MultipartFile file) {
        return channelRepository.findWithAuthorById(id)
                .map(channel -> channelMapper.fromRequest(channel.getId(), channel.getAuthor().getId(),
                        channel.getCreatedAt(), request, getAvatar(file)))
                .map(channelRepository::save)
                .map(channelMapper::toResponse)
                .orElseThrow(() -> NotFoundException.of(Channel.class, id));
    }

    @Override
    public Page<ChannelFilterResponse> findAllByFilter(String title, Language language, String category, Pageable pageable) {
        return channelRepository.findAllByFilter(title, language, category, pageable)
                .map(channelMapper::toFilterResponse);
    }

    @Override
    public ChannelDetailedInformationResponse findDetailedInformationById(Long id) {
        return channelRepository.findWithAuthorAndSubscriptionsById(id)
                .map(channelMapper::toDetailedInformationResponse)
                .orElseThrow(() -> NotFoundException.of(Channel.class, id));
    }

    private byte[] getAvatar(MultipartFile file) {
        try {
            return Objects.isNull(file) ? null : file.getBytes();
        } catch (IOException e) {
            throw new MultipartGetBytesException("Error to extract avatar");
        }
    }
}