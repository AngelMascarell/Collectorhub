package com.collectorhub.collectorhub.services;

import com.collectorhub.collectorhub.database.entities.GenreEntity;
import com.collectorhub.collectorhub.dto.GenreDto;
import com.collectorhub.collectorhub.dto.MangaDto;

import java.util.List;
import java.util.UUID;

public interface GenreService {

    public GenreDto findByName(String name);

    public GenreDto getGenreById(UUID id);

    public void deleteGenre(UUID id);

    public GenreDto updateGenre(GenreDto genre, UUID id);

    public GenreDto createGenre(GenreDto genreDto);

    public List<GenreDto> getAllGenres();

}
