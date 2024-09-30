package com.collectorhub.collectorhub.controller.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.util.UUID;

@Validated
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GenreResponse implements Serializable {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

}
