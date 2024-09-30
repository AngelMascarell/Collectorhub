package com.collectorhub.collectorhub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class GenreDto {

    private Long id;
    private String name;

}
