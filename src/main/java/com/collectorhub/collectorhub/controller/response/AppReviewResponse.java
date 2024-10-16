package com.collectorhub.collectorhub.controller.response;

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
public class AppReviewResponse implements Serializable {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "userId")
    private Long userId;

    @JsonProperty(value = "rate")
    private int rate;

    @JsonProperty(value = "comment")
    private String comment;

    @JsonProperty(value = "date")
    private LocalDate date;

}
