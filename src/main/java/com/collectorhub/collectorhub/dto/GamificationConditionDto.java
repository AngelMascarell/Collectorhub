package com.collectorhub.collectorhub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GamificationConditionDto {

    private Long id;
    private String type;
    private int threshold;

}
