package com.collectorhub.collectorhub.controller;

import com.collectorhub.collectorhub.controller.request.GenreRequest;
import com.collectorhub.collectorhub.controller.response.GenreResponse;
import com.collectorhub.collectorhub.dto.mappers.AbstractGenreDtoMapper;
import com.collectorhub.collectorhub.services.GenreService;
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
@RequestMapping("/api/genre")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @Autowired
    private AbstractGenreDtoMapper genreDtoMapper;

    @PostMapping
    public ResponseEntity<GenreResponse> createGenre(@Valid @RequestBody GenreRequest genreRequest) {
        GenreResponse createdGenre = genreDtoMapper.fromGenreDtoToGenreResponse(
                genreService.createGenre(genreDtoMapper.fromGenreRequestToGenreDto(genreRequest))
        );
        return new ResponseEntity<>(createdGenre, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreResponse> getGenreById(@PathVariable UUID id) {
        GenreResponse genre = genreDtoMapper.fromGenreDtoToGenreResponse(genreService.getGenreById(id));
        return ResponseEntity.ok(genre);
    }

   @GetMapping
    public ResponseEntity<List<GenreResponse>> getAllGenres() {
        List<GenreResponse> allGenres = genreDtoMapper.fromGenreDtoListToGenreResponseList(genreService.getAllGenres());
        return ResponseEntity.ok(allGenres);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreResponse> updateGenre(@PathVariable UUID id, @RequestBody GenreRequest genreRequest) {
        GenreResponse updatedGenre = genreDtoMapper.fromGenreDtoToGenreResponse(
                genreService.updateGenre(genreDtoMapper.fromGenreRequestToGenreDto(genreRequest), id)
        );
        return ResponseEntity.ok(updatedGenre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable UUID id) {
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/countAll")
    public ResponseEntity<Long> getCountGenres() {
        long genreCount = genreService.countAllGenres();
        return ResponseEntity.ok(genreCount);
    }
}

