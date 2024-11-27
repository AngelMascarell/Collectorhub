package com.collectorhub.collectorhub.dto.mappers;

import com.collectorhub.collectorhub.controller.request.RateRequest;
import com.collectorhub.collectorhub.controller.response.RateResponse;
import com.collectorhub.collectorhub.controller.response.RateResponseList;
import com.collectorhub.collectorhub.database.entities.RateEntity;
import com.collectorhub.collectorhub.dto.RateDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AbstractRateDtoMapper {

    RateDto fromRateEntityToRateDto(RateEntity rateEntity);

    List<RateDto> fromRateEntityListToRateDtoList(List<RateEntity> rateEntityList);

    RateDto fromRateRequestToRateDto(RateRequest rateRequest);

    RateResponse fromRateDtoToRateResponse(RateDto rateDto);

    //RateResponseList fromRateDtoListToRateResponseList(List<RateDto> rateDtoList);
    List<RateResponse> fromRateDtoListToRateResponseList(List<RateDto> rateDtoList);

    RateEntity fromRateDtoToRateEntity(RateDto rateDto);

}
