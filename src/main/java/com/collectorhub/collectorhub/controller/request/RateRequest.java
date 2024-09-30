package com.collectorhub.collectorhub.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.time.LocalDate;

@Validated
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RateRequest implements Serializable {

    @JsonProperty(value = "userId")
    private Long userId;

    @JsonProperty(value = "mangaId")
    private Long mangaId;

    @JsonProperty(value = "rate")
    private int rate;

    @JsonProperty(value = "comment")
    private String comment;

    @JsonProperty(value = "date")
    private LocalDate date;

}
