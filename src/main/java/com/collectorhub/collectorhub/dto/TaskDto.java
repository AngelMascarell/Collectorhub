package com.collectorhub.collectorhub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TaskDto {

    private Long id;

    private String description;
    private boolean isCompleted;

    private String title;
    private String taskType;

}
