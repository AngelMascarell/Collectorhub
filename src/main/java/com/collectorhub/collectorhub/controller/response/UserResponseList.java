package com.collectorhub.collectorhub.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.util.List;

@Validated
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserResponseList implements Serializable {

    private List<UserResponse> userResponseList;

}
