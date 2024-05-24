package com.collectorhub.collectorhub.dto.mappers;

import com.collectorhub.collectorhub.database.entities.RateEntity;
import com.collectorhub.collectorhub.dto.RateDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AbstractRateDtoMapper {

    RateDto fromRateEntityToRateDto(RateEntity rateEntity);

    List<RateDto> fromRateEntityListToRateDtoList(List<RateEntity> rateEntityList);

}
