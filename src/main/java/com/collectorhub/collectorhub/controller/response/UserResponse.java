package com.collectorhub.collectorhub.controller.response;

import com.collectorhub.collectorhub.dto.MangaDto;
import com.collectorhub.collectorhub.dto.RoleDto;
import com.collectorhub.collectorhub.dto.TaskDto;
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
public class UserResponse implements Serializable {

    @JsonProperty(value = "userId")
    private Long id;

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

    @JsonProperty(value = "isPremium")
    private boolean isPremium;

    @JsonProperty(value = "premiumStartDate")
    private LocalDate premiumStartDate;

    @JsonProperty(value = "premiumEndDate")
    private LocalDate premiumEndDate;

    @JsonProperty(value = "role")
    private RoleDto role;

    @JsonProperty(value = "tasks")
    private List<TaskDto> tasks;

    @JsonProperty(value = "profileImageUrl")
    private String profileImageUrl;

}
