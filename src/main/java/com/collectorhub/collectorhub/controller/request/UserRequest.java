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

    @JsonProperty(value = "username")
    private String username;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "birthdate")
    private LocalDate birthdate;

    @JsonProperty(value = "registerDate")
    private LocalDate registerDate;

    @JsonProperty(value = "password")
    private String password;

    @JsonProperty(value = "mangas")
    private List<MangaDto> mangas;

    @JsonProperty(value = "isPremium")
    private boolean isPremium;

    @JsonProperty(value = "premiumStartDate")
    private LocalDate premiumStartDate;

    @JsonProperty(value = "premiumEndDate")
    private LocalDate premiumEndDate;

    @JsonProperty(value = "profileImageUrl")
    private String profileImageUrl;

}
