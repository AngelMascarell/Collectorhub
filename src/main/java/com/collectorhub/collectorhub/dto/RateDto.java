package com.collectorhub.collectorhub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class RateDto {

    private UUID id;
    private UUID userId;
    private UUID mangaId;
    private int rate;
    private String comment;
    private LocalDate date;

}
