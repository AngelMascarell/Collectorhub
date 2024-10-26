package com.collectorhub.collectorhub.dto.mappers;

import com.collectorhub.collectorhub.controller.request.MangaRequest;
import com.collectorhub.collectorhub.controller.response.MangaResponse;
import com.collectorhub.collectorhub.controller.response.ObtainMangasResponse;
import com.collectorhub.collectorhub.database.entities.MangaEntity;
import com.collectorhub.collectorhub.dto.MangaDto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AbstractMangaDtoMapper {

    default MangaDto fromMangaEntityToMangaDto(MangaEntity manga) {
        if (manga == null) {
            return null;
        }

        MangaDto mangaDto = new MangaDto();
        mangaDto.setId(manga.getId());
        mangaDto.setTitle(manga.getTitle());
        mangaDto.setAuthor(manga.getAuthor());

        // Asegúrate de que estás asignando el genreId correctamente
        if (manga.getGenre() != null) {
            mangaDto.setGenreId(manga.getGenre().getId());
        }

        return mangaDto;
    }

    default List<MangaDto> fromMangaEntityListToMangaDtoList(List<MangaEntity> mangas) {
        return mangas.stream()
                .map(this::fromMangaEntityToMangaDto)
                .collect(Collectors.toList());
    }

    MangaDto fromMangaRequestToMangaDto(MangaRequest mangaRequest);

    MangaResponse fromMangaDtoToMangaResponse(MangaDto manga);

    List<MangaResponse> fromMangaDtoListToMangaResponseList(List<MangaDto> mangaDtoList);

    default ObtainMangasResponse fromMangaDtoListToObtainMangaResponse(List<MangaDto> mangaDtoList) {
        List<MangaResponse> mangaResponses = mangaDtoList.stream()
                .map(this::fromMangaDtoToMangaResponse)
                .collect(Collectors.toList());

        ObtainMangasResponse response = new ObtainMangasResponse();
        response.setMangaResponseList(mangaResponses);
        return response;
    }
}
