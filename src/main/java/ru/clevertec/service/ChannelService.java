package ru.clevertec.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import ru.clevertec.dto.channel.ChannelDetailedInformationResponse;
import ru.clevertec.dto.channel.ChannelFilterResponse;
import ru.clevertec.dto.channel.ChannelRequest;
import ru.clevertec.dto.channel.ChannelResponse;
import ru.clevertec.enam.Language;

public interface ChannelService {

    ChannelResponse saveByAuthorId(Long authorId, ChannelRequest request, MultipartFile file);

    ChannelResponse updateById(Long id, ChannelRequest request, MultipartFile file);

    Page<ChannelFilterResponse> findAllByFilter(String title, Language language, String category, Pageable pageable);

    ChannelDetailedInformationResponse findDetailedInformationById(Long id);
}