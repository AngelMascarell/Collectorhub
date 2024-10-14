package com.collectorhub.collectorhub.controller.request;

import com.collectorhub.collectorhub.dto.MangaDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Validated
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserRequest implements Serializable {

    @JsonProperty(value = "userId")
    private Long userId;

    @JsonProperty(value = "username")
    private String username;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "birthdate")
    private LocalDate birthdate;

    @JsonProperty(value = "registerDate")
    private LocalDate registerDate;

    @JsonProperty(value = "mangas")
    private List<MangaDto> mangas;

}