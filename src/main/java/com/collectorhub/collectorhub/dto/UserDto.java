package com.collectorhub.collectorhub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private String password;
    private LocalDate birthdate;
    private LocalDate registerDate;
    private List<MangaDto> mangas;

    private boolean isPremium = false;

    private LocalDate premiumStartDate = null;

    private LocalDate premiumEndDate = null;

    private RoleDto role;

}
