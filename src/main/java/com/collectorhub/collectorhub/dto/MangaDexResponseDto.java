package com.collectorhub.collectorhub.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MangaDexResponseDto {

    private String id;
    private String title;
    private String description;
    private boolean isCompleted;
    private String publicationDemographic;
    private List<String> genres;
    private int chapters;
    private String imageUrl;

    private String synopsis;
    private LocalDate releaseDate;

}
