package com.collectorhub.collectorhub.controller;

import com.collectorhub.collectorhub.controller.request.MangaRequest;
import com.collectorhub.collectorhub.controller.response.MangaResponse;
import com.collectorhub.collectorhub.controller.response.ObtainMangasResponse;
import com.collectorhub.collectorhub.database.entities.MangaEntity;
import com.collectorhub.collectorhub.dto.MangaDexResponseDto;
import com.collectorhub.collectorhub.dto.MangaDto;
import com.collectorhub.collectorhub.dto.mappers.AbstractMangaDtoMapper;
import com.collectorhub.collectorhub.services.MangaDexService;
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
@RequestMapping("/manga")
public class MangaController {

    @Autowired
    private MangaService mangaService;

    @Autowired
    private AbstractMangaDtoMapper mangaDtoMapper;

    @Autowired
    private MangaDexService mangaDexService;

    @PostMapping
    public ResponseEntity<MangaResponse> createManga(@Valid @RequestBody MangaRequest mangaRequest) {
        MangaResponse createdManga = mangaDtoMapper.fromMangaDtoToMangaResponse(mangaService.createManga(mangaDtoMapper.fromMangaRequestToMangaDto(mangaRequest)));
        return new ResponseEntity<>(createdManga, HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<MangaResponse> searchMangaByName(@RequestParam String name) {
        MangaEntity manga = mangaService.findMangaByTittle(name);
        if (manga != null) {
            return ResponseEntity.ok(mangaDtoMapper.fromMangaDtoToMangaResponse(mangaDtoMapper.fromMangaEntityToMangaDto(manga)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MangaResponse> getMangaById(@PathVariable Long id) {
        MangaResponse manga = mangaDtoMapper.fromMangaDtoToMangaResponse(mangaService.getMangaById(id));
        return ResponseEntity.ok(manga);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ObtainMangasResponse> getAllMangas() {
        List<MangaDto> allMangas = mangaService.getAllMangas();
        ObtainMangasResponse response = mangaDtoMapper.fromMangaDtoListToObtainMangaResponse(allMangas);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getCompleted")
    public ResponseEntity<ObtainMangasResponse> getCompletedMangas() {
        List<MangaDto> completedMangas = mangaService.getCompletedMangas();
        ObtainMangasResponse response = mangaDtoMapper.fromMangaDtoListToObtainMangaResponse(completedMangas);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getUnder100")
    public ResponseEntity<ObtainMangasResponse> getUnder100ChapterMangas() {
        List<MangaDto> completedMangas = mangaService.getUnder100Mangas();
        ObtainMangasResponse response = mangaDtoMapper.fromMangaDtoListToObtainMangaResponse(completedMangas);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/personalized/{userId}")
    public ResponseEntity<ObtainMangasResponse> getPersonalizedMangas(@PathVariable Long userId) {
        List<MangaDto> personalizedMangas = mangaService.getPersonalizedMangas(userId);
        ObtainMangasResponse response = mangaDtoMapper.fromMangaDtoListToObtainMangaResponse(personalizedMangas);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MangaResponse> updateManga(@PathVariable UUID id, @RequestBody MangaRequest mangaRequest) {
        MangaResponse updatedManga = mangaDtoMapper.fromMangaDtoToMangaResponse(mangaService.updateManga(mangaDtoMapper.fromMangaRequestToMangaDto(mangaRequest), id));
        return ResponseEntity.ok(updatedManga);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManga(@PathVariable UUID id) {
        mangaService.deleteManga(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/existsByTitle/{title}")
    public ResponseEntity<Boolean> existsByTitle(@PathVariable String title) {
        boolean exists = mangaService.existsByTitle(title);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/countAll")
    public ResponseEntity<Long> getCountUsers() {
        long mangaCount = mangaService.countAllMangas();
        return ResponseEntity.ok(mangaCount);
    }

    @GetMapping("/recent-30-days")
    public ResponseEntity<ObtainMangasResponse> getRecentMangas() {
        List<MangaDto> recentMangas = mangaService.findMangasReleasedInLast30Days();

        ObtainMangasResponse mangaListResponse = mangaDtoMapper.fromMangaDtoListToObtainMangaResponse(recentMangas);
        return ResponseEntity.ok(mangaListResponse);
    }


    //TODO:MANGA DEX ENDPOINTS

    @GetMapping("/mangadex/{id}")
    public ResponseEntity<MangaDexResponseDto> getMangaById(@PathVariable("id") String mangaId) {
        MangaDexResponseDto manga = mangaDexService.getMangaById(mangaId);
        if (manga == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(manga, HttpStatus.OK);
    }

    @PostMapping("/mangadex/{id}/save")
    public ResponseEntity<MangaEntity> saveManga(@PathVariable("id") String mangaId) {
        MangaEntity savedManga = mangaDexService.saveManga(mangaId);
        return new ResponseEntity<>(savedManga, HttpStatus.CREATED);
    }

    @GetMapping("/mangadex")
    public ResponseEntity<MangaDexResponseDto> getRandomManga() {
        MangaDexResponseDto manga = mangaDexService.getRandomManga();
        if (manga == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(manga, HttpStatus.OK);
    }

    @PostMapping("/mangadex/saveRandom")
    public ResponseEntity<MangaEntity> saveRandomManga() {
        MangaEntity savedManga = mangaDexService.saveRandomManga();
        return new ResponseEntity<>(savedManga, HttpStatus.CREATED);
    }

    @GetMapping("/mangadex/get-mangas")
    public List<MangaDexResponseDto> searchMangas(
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "title", required = false) String title) {

        return mangaDexService.searchMangas(limit, offset, title);
    }


}
