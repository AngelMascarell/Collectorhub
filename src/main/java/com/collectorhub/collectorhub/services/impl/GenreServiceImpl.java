package com.collectorhub.collectorhub.services.impl;

import com.collectorhub.collectorhub.database.entities.GenreEntity;
import com.collectorhub.collectorhub.database.repositories.GenreRepository;
import com.collectorhub.collectorhub.dto.GenreDto;
import com.collectorhub.collectorhub.dto.mappers.AbstractGenreDtoMapper;
import com.collectorhub.collectorhub.services.GenreService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Override
    public GenreDto getGenreById(UUID id) {
        Optional<GenreEntity> genreEntity = genreRepository.findById(id);
        if (genreEntity.isPresent()) {
            GenreEntity genre = genreEntity.get();
            return mapEntityToDto(genre);
        } else {
            throw new EntityNotFoundException("Genre not found with id: " + id);
        }
    }

    private GenreDto mapEntityToDto(GenreEntity genreEntity) {
        return GenreDto.builder()
                .id(genreEntity.getId())
                .name(genreEntity.getName())
                .build();
    }

    @Override
    public void deleteGenre(UUID id) {
        genreRepository.deleteById(id);
    }

    @Override
    public GenreDto updateGenre(GenreDto genre, UUID id) {
        GenreEntity existingGenre = genreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Genre not found with id: " + id));

        existingGenre.setName(genre.getName());

        GenreEntity updatedGenre = genreRepository.save(existingGenre);
        return genreDtoMapper.fromGenreEntityToGenreDto(updatedGenre);
    }

    @Override
    public GenreDto createGenre(GenreDto genreDto) {
        GenreEntity genreEntity = GenreEntity.builder()
                .name(genreDto.getName())
                .build();

        GenreEntity savedGenreEntity = genreRepository.save(genreEntity);

        return genreDtoMapper.fromGenreEntityToGenreDto(savedGenreEntity);
    }

    @Override
    public List<GenreDto> getAllGenres() {
        return genreDtoMapper.fromGenreEntityListToGenreDtoList(genreRepository.findAll());

    }

}
