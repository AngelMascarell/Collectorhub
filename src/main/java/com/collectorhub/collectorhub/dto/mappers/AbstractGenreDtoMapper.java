package com.collectorhub.collectorhub.dto.mappers;

import com.collectorhub.collectorhub.controller.request.GenreRequest;
import com.collectorhub.collectorhub.controller.response.GenreResponse;
import com.collectorhub.collectorhub.database.entities.GenreEntity;
import com.collectorhub.collectorhub.database.entities.MangaEntity;
import com.collectorhub.collectorhub.dto.GenreDto;
import com.collectorhub.collectorhub.dto.MangaDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AbstractGenreDtoMapper {

    GenreDto fromGenreEntityToGenreDto(GenreEntity genreEntity);

    List<GenreDto> fromGenreEntityListToGenreDtoList(List<GenreEntity> genreEntityList);

    GenreResponse fromGenreDtoToGenreResponse(GenreDto dto);

    GenreDto fromGenreRequestToGenreDto(GenreRequest request);

    List<GenreResponse> fromGenreDtoListToGenreResponseList(List<GenreDto> genreDtoList);
}
