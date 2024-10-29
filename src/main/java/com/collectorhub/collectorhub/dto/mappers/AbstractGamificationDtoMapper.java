package com.collectorhub.collectorhub.dto.mappers;

import com.collectorhub.collectorhub.controller.request.GamificationRequest;
import com.collectorhub.collectorhub.controller.response.GamificationResponse;
import com.collectorhub.collectorhub.database.entities.GamificationEntity;
import com.collectorhub.collectorhub.dto.GamificationConditionDto;
import com.collectorhub.collectorhub.dto.GamificationDto;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AbstractGamificationDtoMapper {

    GamificationDto fromGamificationEntityToGamificationDto(GamificationEntity gamificationEntity);

    List<GamificationDto> fromGamificationEntityListToGamificationDtoList(List<GamificationEntity> gamificationEntityList);

    GamificationDto fromGamificationRequestToGamificationDto(GamificationRequest gamificationRequest);

    GamificationResponse fromGamificationDtoToGamificationResponse(GamificationDto gamificationDto);

    List<GamificationResponse> fromGamificationDtoListToGamificationResponseList(List<GamificationDto> gamificationDtoList);

    GamificationEntity fromGamificationDtoToGamificationEntity(GamificationDto gamificationDto);

    GamificationResponse fromGamificationRequestToGamificationResponse(GamificationRequest gamificationRequest);


}
