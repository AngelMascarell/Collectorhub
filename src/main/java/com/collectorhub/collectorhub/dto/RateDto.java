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

    private Long id;
    private Long userId;
    private Long mangaId;
    private int rate;
    private String comment;
    private LocalDate date;

}
