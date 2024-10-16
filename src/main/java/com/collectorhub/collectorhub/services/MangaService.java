package com.collectorhub.collectorhub.services;

import com.collectorhub.collectorhub.database.entities.MangaEntity;
import com.collectorhub.collectorhub.dto.MangaDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MangaService {

    public MangaDto findByTitle(String title);

    public List<MangaDto> findByAuthor(String author);

    public List<MangaDto> findByGenreName(String genreName);

    public List<MangaDto> findByChapters(int chapters);

    public List<MangaDto> findByCompletedTrue();

    public MangaDto createManga(MangaDto manga);

    public MangaDto getMangaById(UUID id);

    public List<MangaDto> getAllMangas();

    public MangaDto updateManga(MangaDto manga, UUID id);

    public void deleteManga(UUID id);

    public boolean existsByTitle(String tittle);

    public long countAllMangas();
}
