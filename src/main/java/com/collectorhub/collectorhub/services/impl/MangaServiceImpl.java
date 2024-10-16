package com.collectorhub.collectorhub.services.impl;

import com.collectorhub.collectorhub.database.entities.GenreEntity;
import com.collectorhub.collectorhub.database.entities.MangaEntity;
import com.collectorhub.collectorhub.database.repositories.GenreRepository;
import com.collectorhub.collectorhub.database.repositories.MangaRepository;
import com.collectorhub.collectorhub.dto.MangaDto;
import com.collectorhub.collectorhub.dto.mappers.AbstractMangaDtoMapper;
import com.collectorhub.collectorhub.services.MangaService;
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
public class MangaServiceImpl implements MangaService {

    @Autowired
    private MangaRepository mangaRepository;

    @Autowired
    private AbstractMangaDtoMapper mangaDtoMapper;

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public MangaDto findByTitle(String title) {
        return mangaDtoMapper.fromMangaEntityToMangaDto(mangaRepository.findByTitle(title));
    }

    @Override
    public List<MangaDto> findByAuthor(String author) {
        return mangaDtoMapper.fromMangaEntityListToMangaDtoList(mangaRepository.findByAuthor(author));
    }

    @Override
    public List<MangaDto> findByGenreName(String genreName) {
        return mangaDtoMapper.fromMangaEntityListToMangaDtoList(mangaRepository.findByGenreName(genreName));
    }

    @Override
    public List<MangaDto> findByChapters(int chapters) {
        return mangaDtoMapper.fromMangaEntityListToMangaDtoList(mangaRepository.findByChapters(chapters));
    }

    @Override
    public List<MangaDto> findByCompletedTrue() {
        return mangaDtoMapper.fromMangaEntityListToMangaDtoList(mangaRepository.findByCompletedTrue());
    }

    @Override
    public MangaDto createManga(MangaDto mangaDto) {
        // Comprobar si ya existe un manga con el mismo título
        if (mangaRepository.existsByTitle(mangaDto.getTitle())) {
            throw new IllegalArgumentException("A manga with this title already exists: " + mangaDto.getTitle());
        }

        // Crear la entidad de manga a partir del DTO
        MangaEntity mangaEntity = MangaEntity.builder()
                .author(mangaDto.getAuthor())
                .completed(mangaDto.isCompleted())
                .chapters(mangaDto.getChapters())
                .title(mangaDto.getTitle())
                .build();

        // Comprobar si el genreId no es nulo y asociarlo a la entidad de manga
        if (mangaDto.getGenreId() != null) {
            GenreEntity genre = genreRepository.findById(mangaDto.getGenreId());
            if (genre == null) {
                throw new EntityNotFoundException("Genre not found with id: " + mangaDto.getGenreId());
            }
            mangaEntity.setGenre(genre);
        }

        // Guardar la entidad de manga en la base de datos
        MangaEntity savedMangaEntity = mangaRepository.save(mangaEntity);

        // Convertir la entidad guardada de vuelta a DTO y devolverla
        return mangaDtoMapper.fromMangaEntityToMangaDto(savedMangaEntity);
    }


    @Override
    public MangaDto getMangaById(UUID id) {
        Optional<MangaEntity> mangaOptional = mangaRepository.findById(id);
        if (mangaOptional.isPresent()) {
            MangaEntity mangaEntity = mangaOptional.get();
            return mapEntityToDto(mangaEntity);
        } else {
            throw new EntityNotFoundException("Manga not found with id: " + id);
        }
    }

    private MangaDto mapEntityToDto(MangaEntity mangaEntity) {
        return MangaDto.builder()
                .id(mangaEntity.getId())
                .title(mangaEntity.getTitle())
                .author(mangaEntity.getAuthor())
                .genreId(mangaEntity.getGenre() != null ? mangaEntity.getGenre().getId() : null)
                .chapters(mangaEntity.getChapters())
                .completed(mangaEntity.isCompleted())
                //.propietarios(mangaEntity.getPropietarios())
                .build();
    }

    @Override
    public List<MangaDto> getAllMangas() {
        return mangaDtoMapper.fromMangaEntityListToMangaDtoList(mangaRepository.findAll());
    }

    @Override
    public MangaDto updateManga(MangaDto mangaDto, UUID id) {
        MangaEntity existingManga = mangaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Manga not found with id: " + id));

        existingManga.setTitle(mangaDto.getTitle());
        existingManga.setAuthor(mangaDto.getAuthor());
        existingManga.setChapters(mangaDto.getChapters());
        existingManga.setCompleted(mangaDto.isCompleted());

        MangaEntity updatedManga = mangaRepository.save(existingManga);

        return mangaDtoMapper.fromMangaEntityToMangaDto(updatedManga);
    }


    @Override
    public void deleteManga(UUID id) {
        mangaRepository.deleteById(id);
    }

    @Override
    public boolean existsByTitle(String title) {
        return mangaRepository.existsByTitle(title);
    }

    @Override
    public long countAllMangas() {
        return mangaRepository.count();
    }

}
