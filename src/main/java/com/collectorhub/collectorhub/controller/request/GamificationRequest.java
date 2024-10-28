package com.collectorhub.collectorhub.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class GamificationRequest implements Serializable {

    private String title;
    private String description;
    private String imageUrl;

}
