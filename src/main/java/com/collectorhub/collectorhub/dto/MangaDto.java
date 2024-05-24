package com.collectorhub.collectorhub.dto;

import com.collectorhub.collectorhub.database.entities.UserEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class MangaDto {

    private UUID id;

    private String title;

    private String author;

    private UUID genreId;

    private int chapters;

    private boolean completed;

}
