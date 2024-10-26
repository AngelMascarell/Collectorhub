package com.collectorhub.collectorhub.services;

import com.collectorhub.collectorhub.controller.request.MangaListRequest;
import com.collectorhub.collectorhub.database.entities.MangaEntity;
import com.collectorhub.collectorhub.database.entities.MangaListEntity;
import com.collectorhub.collectorhub.dto.MangaDto;
import com.collectorhub.collectorhub.dto.MangaListDto;

import java.util.List;
import java.util.UUID;

public interface MangaListService {

    public MangaListDto crearListaDeMangas(MangaListDto mangaListDto);

    public List<MangaDto> obtenerMangasDeLista(UUID listaId);

    MangaListDto fromMangaListRequestToMangaListDto(MangaListRequest request);


}
