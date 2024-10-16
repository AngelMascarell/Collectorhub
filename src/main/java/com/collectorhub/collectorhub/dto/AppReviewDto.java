package com.collectorhub.collectorhub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class AppReviewDto {

    private Long id;
    private Long userId;
    private int rate;
    private String comment;
    private LocalDate date;

}
