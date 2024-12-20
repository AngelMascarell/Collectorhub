package com.collectorhub.collectorhub.dto;

import com.collectorhub.collectorhub.database.entities.UserEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MangaDto {

    private Long id;

    private String title;

    private String author;

    private Long genreId;

    private int chapters;

    private boolean completed;

    private String imageUrl;

    private String synopsis;
    private LocalDate releaseDate;

}
