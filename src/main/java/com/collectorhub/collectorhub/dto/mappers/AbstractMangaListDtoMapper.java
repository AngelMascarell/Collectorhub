package com.collectorhub.collectorhub.dto.mappers;

import com.collectorhub.collectorhub.controller.request.GenreRequest;
import com.collectorhub.collectorhub.controller.request.MangaListRequest;
import com.collectorhub.collectorhub.controller.response.GenreResponse;
import com.collectorhub.collectorhub.controller.response.MangaListResponse;
import com.collectorhub.collectorhub.database.entities.GenreEntity;
import com.collectorhub.collectorhub.database.entities.MangaEntity;
import com.collectorhub.collectorhub.database.entities.MangaListEntity;
import com.collectorhub.collectorhub.database.repositories.GenreRepository;
import com.collectorhub.collectorhub.database.repositories.MangaRepository;
import com.collectorhub.collectorhub.dto.GenreDto;
import com.collectorhub.collectorhub.dto.MangaDto;
import com.collectorhub.collectorhub.dto.MangaListDto;
import com.collectorhub.collectorhub.services.MangaService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AbstractMangaListDtoMapper {

    //MangaListDto fromMangaListEntityToMangaListDto(MangaListEntity mangaListEntity);

    List<MangaListDto> fromMangaListEntityListToMangaListDtoList(List<MangaListEntity> mangaListEntityList);

    MangaListResponse fromMangaListDtoToMangaListResponse(MangaListDto dto);
    //MangaListDto fromMangaListRequestToMangaListDto(MangaListRequest request);

    List<MangaListResponse> fromMangaListDtoListToMangaListResponseList(List<MangaListDto> mangaListDtoList);

    default MangaDto fromMangaEntityToMangaDto(MangaEntity mangaEntity) {
        return MangaDto.builder()
                .id(mangaEntity.getId())
                .title(mangaEntity.getTitle())
                .author(mangaEntity.getAuthor())
                .genreId(mangaEntity.getGenre() != null ? mangaEntity.getGenre().getId() : null)
                .chapters(mangaEntity.getChapters())
                .completed(mangaEntity.isCompleted())
                .build();
    }

    default MangaEntity fromMangaDtoToMangaEntity(MangaDto mangaDto, GenreRepository genreRepository) {
        MangaEntity mangaEntity = new MangaEntity();
        mangaEntity.setId(mangaDto.getId());
        mangaEntity.setTitle(mangaDto.getTitle());
        mangaEntity.setAuthor(mangaDto.getAuthor());
        mangaEntity.setChapters(mangaDto.getChapters());
        mangaEntity.setCompleted(mangaDto.isCompleted());

        // Si genreId no es nulo, busca y asigna el género
        if (mangaDto.getGenreId() != null) {
            mangaEntity.setGenre(genreRepository.findById(mangaDto.getGenreId()));
        }

        return mangaEntity;
    }

    default MangaListDto fromMangaListEntityToMangaListDto(MangaListEntity mangaListEntity) {
        return MangaListDto.builder()
                .id(mangaListEntity.getId())
                .name(mangaListEntity.getName())
                .description(mangaListEntity.getDescription())
                .mangas(mangaListEntity.getMangas().stream()
                        .map(this::fromMangaEntityToMangaDto)
                        .collect(Collectors.toList()))
                .build();
    }

}
