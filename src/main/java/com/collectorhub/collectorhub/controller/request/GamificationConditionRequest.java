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
public class GamificationConditionRequest implements Serializable {

    private String type;
    private int threshold;

}
