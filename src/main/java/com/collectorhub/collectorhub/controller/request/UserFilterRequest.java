package com.collectorhub.collectorhub.controller.request;

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
public class UserFilterRequest implements Serializable {

    private String username;
    private String email;
    private LocalDate registerDate;
    private LocalDate birthdate;
    private String role;

}
