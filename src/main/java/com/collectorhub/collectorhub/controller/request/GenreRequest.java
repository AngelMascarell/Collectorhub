package com.collectorhub.collectorhub.controller.request;

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
public class GenreRequest implements Serializable {

    @JsonProperty(value = "name")
    private String name;

}
