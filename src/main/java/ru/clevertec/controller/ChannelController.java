package ru.clevertec.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.clevertec.dto.channel.ChannelDetailedInformationResponse;
import ru.clevertec.dto.channel.ChannelFilterResponse;
import ru.clevertec.dto.channel.ChannelRequest;
import ru.clevertec.dto.channel.ChannelResponse;
import ru.clevertec.enam.Language;
import ru.clevertec.service.ChannelService;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/channels")
public class ChannelController {

    private final ChannelService channelService;

    @PostMapping
    public ResponseEntity<ChannelResponse> saveByAuthorId(@RequestParam @Positive Long authorId,
                                                          @RequestPart @Valid ChannelRequest request,
                                                          @RequestPart(required = false) MultipartFile file) {
        return ResponseEntity.status(HttpStatus.CREATED).body(channelService.saveByAuthorId(authorId, request, file));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChannelResponse> updateById(@PathVariable @Positive Long id,
                                                      @RequestPart @Valid ChannelRequest request,
                                                      @RequestPart(required = false) MultipartFile file) {
        return ResponseEntity.status(HttpStatus.OK).body(channelService.updateById(id, request, file));
    }

    @GetMapping
    public ResponseEntity<Page<ChannelFilterResponse>> findAllByFilter(@RequestParam(required = false) String title,
                                                                       @RequestParam(required = false) Language language,
                                                                       @RequestParam(required = false) String category,
                                                                       Pageable pageable) {
        return ResponseEntity.ok(channelService.findAllByFilter(title, language, category, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChannelDetailedInformationResponse> findDetailedInformationById(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(channelService.findDetailedInformationById(id));
    }
}