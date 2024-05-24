package com.collectorhub.collectorhub.controller;

import com.collectorhub.collectorhub.controller.request.MangaRequest;
import com.collectorhub.collectorhub.controller.response.MangaResponse;
import com.collectorhub.collectorhub.dto.mappers.AbstractMangaDtoMapper;
import com.collectorhub.collectorhub.services.MangaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/api/manga")
public class MangaController {

    @Autowired
    private MangaService mangaService;

    @Autowired
    private AbstractMangaDtoMapper mangaDtoMapper;

    @PostMapping
    public ResponseEntity<MangaResponse> createManga(@Valid @RequestBody MangaRequest mangaRequest) {
        log.info("Received MangaRequest: {}", mangaRequest);
        MangaResponse createdManga = mangaDtoMapper.fromMangaDtotoMangaResponse(mangaService.createManga(mangaDtoMapper.fromMangaRequestToMangaDto(mangaRequest)));
        return new ResponseEntity<>(createdManga, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MangaResponse> getMangaById(@PathVariable UUID id) {
        MangaResponse manga = mangaDtoMapper.fromMangaDtotoMangaResponse(mangaService.getMangaById(id));
        return ResponseEntity.ok(manga);
    }

    @GetMapping
    public ResponseEntity<List<MangaResponse>> getAllMangas() {
        List<MangaResponse> allMangas = mangaDtoMapper.fromMangaDtoListToMangaResponseList(mangaService.getAllMangas());
        return ResponseEntity.ok(allMangas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MangaResponse> updateManga(@PathVariable UUID id, @RequestBody MangaRequest mangaRequest) {
        MangaResponse updatedManga = mangaDtoMapper.fromMangaDtotoMangaResponse(mangaService.updateManga(mangaDtoMapper.fromMangaRequestToMangaDto(mangaRequest), id));
        return ResponseEntity.ok(updatedManga);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManga(@PathVariable UUID id) {
        mangaService.deleteManga(id);
        return ResponseEntity.noContent().build();
    }

}
