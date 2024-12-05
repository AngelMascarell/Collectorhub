package com.collectorhub.collectorhub.services;

import com.collectorhub.collectorhub.database.entities.MangaEntity;
import com.collectorhub.collectorhub.dto.MangaDexResponseDto;

import java.util.List;

public interface MangaDexService {
    public MangaDexResponseDto getMangaById(String mangaId);

    public MangaEntity saveManga(String mangaId);

    public List<MangaDexResponseDto> searchMangas(int limit, int offset, String title);
}
