package com.collectorhub.collectorhub.controller.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Validated
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TaskRequest implements Serializable {

    private String description;
    private boolean isCompleted;

    private String title;
    private String taskType;

}
