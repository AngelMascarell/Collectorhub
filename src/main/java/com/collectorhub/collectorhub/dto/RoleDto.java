package com.collectorhub.collectorhub.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RoleDto {

    private Long id;
    private String name;

}
