package com.collectorhub.collectorhub.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class GamificationDto {

    private Long id;

    private String title;

    private String description;

    private String imageUrl;

    @OneToMany(cascade = CascadeType.ALL)
    private List<GamificationConditionDto> conditions;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
