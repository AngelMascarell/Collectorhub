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
public class MangaRequest implements Serializable {

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "author")
    private String author;

    @JsonProperty(value = "genreId")
    private Long genreId;

    @JsonProperty(value = "chapters")
    private int chapters;

    @JsonProperty(value = "completed")
    private boolean completed;

}
