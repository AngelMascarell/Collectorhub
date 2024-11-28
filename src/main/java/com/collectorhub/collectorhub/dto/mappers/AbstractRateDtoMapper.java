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

    //RateDto fromRateEntityToRateDto(RateEntity rateEntity);

    default RateDto fromRateEntityToRateDto(RateEntity rateEntity) {
        if ( rateEntity == null ) {
            return null;
        }

        RateDto.RateDtoBuilder rateDto = RateDto.builder();

        rateDto.userId( rateEntity.getUser().getId() );
        rateDto.mangaId( rateEntity.getManga().getId() );

        rateDto.id( rateEntity.getId() );
        rateDto.rate( rateEntity.getRate() );
        rateDto.comment( rateEntity.getComment() );
        rateDto.date( rateEntity.getDate() );

        return rateDto.build();
    }

    default RateResponse convertToRateResponse(RateEntity review) {
        return RateResponse.builder()
                .id(review.getId())
                .userId(review.getUser().getId())
                .mangaId(review.getManga().getId())
                .rate(review.getRate())
                .comment(review.getComment())
                .date(review.getDate())
                .build();
    }

    List<RateDto> fromRateEntityListToRateDtoList(List<RateEntity> rateEntityList);

    RateDto fromRateRequestToRateDto(RateRequest rateRequest);

    RateResponse fromRateDtoToRateResponse(RateDto rateDto);

    //RateResponseList fromRateDtoListToRateResponseList(List<RateDto> rateDtoList);
    List<RateResponse> fromRateDtoListToRateResponseList(List<RateDto> rateDtoList);

    RateEntity fromRateDtoToRateEntity(RateDto rateDto);
}
