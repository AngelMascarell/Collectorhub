package com.collectorhub.collectorhub.dto.mappers;

import com.collectorhub.collectorhub.controller.request.MangaRequest;
import com.collectorhub.collectorhub.controller.response.MangaResponse;
import com.collectorhub.collectorhub.database.entities.MangaEntity;
import com.collectorhub.collectorhub.dto.MangaDto;
import jakarta.persistence.MappedSuperclass;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AbstractMangaDtoMapper {

    MangaDto fromMangaEntityToMangaDto(MangaEntity mangaEntity);

    List<MangaDto> fromMangaEntityListToMangaDtoList(List<MangaEntity> mangaEntityList);

    MangaDto fromMangaRequestToMangaDto(MangaRequest mangaRequest);

    MangaResponse fromMangaDtotoMangaResponse(MangaDto manga);

    List<MangaResponse> fromMangaDtoListToMangaResponseList(List<MangaDto> mangaDtoList);
}
