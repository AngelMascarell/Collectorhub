package com.collectorhub.collectorhub.controller.response;


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
public class GamificationResponse implements Serializable {

    @JsonProperty(value = "id")
    private Long id;

    private String title;
    private String description;
    private String imageUrl;

}
