package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.dto.channel.ChannelDetailedInformationResponse;
import ru.clevertec.dto.channel.ChannelFilterResponse;
import ru.clevertec.dto.channel.ChannelRequest;
import ru.clevertec.dto.channel.ChannelResponse;
import ru.clevertec.dto.projection.ChannelFilterProjection;
import ru.clevertec.entity.Channel;

import java.time.LocalDate;

@Mapper
public interface ChannelMapper {

    @Mapping(target = "author.id", source = "authorId")
    Channel fromRequest(Long authorId, ChannelRequest request, byte[] avatar);

    @Mapping(target = "author.id", source = "authorId")
    Channel fromRequest(Long id, Long authorId, LocalDate createdAt, ChannelRequest request, byte[] avatar);

    @Mapping(target = "authorId", source = "channel.author.id")
    ChannelResponse toResponse(Channel channel);

    ChannelFilterResponse toFilterResponse(ChannelFilterProjection projection);

    @Mapping(target = "subscribersCount", expression = "java(channel.getSubscribers().size())")
    ChannelDetailedInformationResponse toDetailedInformationResponse(Channel channel);
}