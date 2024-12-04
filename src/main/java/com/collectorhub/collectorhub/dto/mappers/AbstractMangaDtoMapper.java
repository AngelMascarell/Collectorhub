package com.collectorhub.collectorhub.dto.mappers;

import com.collectorhub.collectorhub.controller.request.MangaRequest;
import com.collectorhub.collectorhub.controller.response.MangaResponse;
import com.collectorhub.collectorhub.controller.response.ObtainMangasResponse;
import com.collectorhub.collectorhub.database.entities.GenreEntity;
import com.collectorhub.collectorhub.database.entities.MangaEntity;
import com.collectorhub.collectorhub.database.repositories.GenreRepository;
import com.collectorhub.collectorhub.dto.MangaDexResponseDto;
import com.collectorhub.collectorhub.dto.MangaDto;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AbstractMangaDtoMapper {

    default MangaEntity fromMangaDexResponseDtoToMangaEntity(MangaDexResponseDto model, GenreRepository genreRepository) {
        MangaEntity mangaEntity = new MangaEntity();
        mangaEntity.setTitle(model.getTitle());
        mangaEntity.setChapters(model.getChapters());
        mangaEntity.setCompleted(model.isCompleted());
        mangaEntity.setImageUrl(model.getImageUrl());

        mangaEntity.setAuthor(generateRandomJapaneseName());

        // Asignar géneros
        List<GenreEntity> genres = model.getGenres().stream()
                .map(genreName -> genreRepository.findByName(genreName)) // Busca el género en la base de datos
                .filter(Objects::nonNull) // Filtra los géneros nulos en caso de que no se encuentren
                .collect(Collectors.toList());

        // Aquí podrías ajustar si necesitas un solo género o una lista
        // Por ejemplo, si solo necesitas el primer género:
        if (!genres.isEmpty()) {
            mangaEntity.setGenre(genres.get(0)); // Asigna el primer género encontrado
        }

        return mangaEntity;
    }

    default MangaDto fromMangaEntityToMangaDto(MangaEntity manga) {
        if (manga == null) {
            return null;
        }

        MangaDto mangaDto = new MangaDto();
        mangaDto.setId(manga.getId());
        mangaDto.setTitle(manga.getTitle());
        mangaDto.setAuthor(manga.getAuthor());
        mangaDto.setChapters(manga.getChapters());
        mangaDto.setCompleted(manga.isCompleted());
        mangaDto.setImageUrl(manga.getImageUrl());

        if (manga.getGenre() != null) {
            mangaDto.setGenreId(manga.getGenre().getId());
        }

        mangaDto.setSynopsis(manga.getSynopsis());
        mangaDto.setReleaseDate(manga.getReleaseDate());

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

    private static String generateRandomJapaneseName() {
        String[] firstNames = {
                "Haruki", "Yuki", "Sakura", "Hiroshi", "Aiko",
                "Riku", "Miyuki", "Kenji", "Nao", "Yumi",
                "Taro", "Emi", "Kazuki", "Sora", "Hana"
        };

        String[] lastNames = {
                "Takahashi", "Tanaka", "Watanabe", "Suzuki",
                "Kobayashi", "Yamamoto", "Matsumoto", "Inoue",
                "Kimura", "Shimizu"
        };

        Random random = new Random();
        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];

        return firstName + " " + lastName;
    }
}
