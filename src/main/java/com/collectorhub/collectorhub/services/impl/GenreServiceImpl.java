package com.collectorhub.collectorhub.services.impl;

import com.collectorhub.collectorhub.database.entities.GenreEntity;
import com.collectorhub.collectorhub.database.repositories.GenreRepository;
import com.collectorhub.collectorhub.dto.GenreDto;
import com.collectorhub.collectorhub.dto.mappers.AbstractGenreDtoMapper;
import com.collectorhub.collectorhub.services.GenreService;
import com.collectorhub.collectorhub.services.MangaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private AbstractGenreDtoMapper genreDtoMapper;

    @Override
    public GenreDto findByName(String name) {
        return genreDtoMapper.fromGenreEntityToGenreDto(genreRepository.findByName(name));
    }

}
