package com.collectorhub.collectorhub.dto.mappers;

import com.collectorhub.collectorhub.controller.request.RateRequest;
import com.collectorhub.collectorhub.controller.response.RateResponse;
import com.collectorhub.collectorhub.database.entities.RateEntity;
import com.collectorhub.collectorhub.dto.RateDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-15T13:10:56+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class AbstractRateDtoMapperImpl implements AbstractRateDtoMapper {

    @Override
    public RateDto fromRateEntityToRateDto(RateEntity rateEntity) {
        if ( rateEntity == null ) {
            return null;
        }

        RateDto.RateDtoBuilder rateDto = RateDto.builder();

        rateDto.id( rateEntity.getId() );
        rateDto.rate( rateEntity.getRate() );
        rateDto.comment( rateEntity.getComment() );
        rateDto.date( rateEntity.getDate() );

        return rateDto.build();
    }

    @Override
    public List<RateDto> fromRateEntityListToRateDtoList(List<RateEntity> rateEntityList) {
        if ( rateEntityList == null ) {
            return null;
        }

        List<RateDto> list = new ArrayList<RateDto>( rateEntityList.size() );
        for ( RateEntity rateEntity : rateEntityList ) {
            list.add( fromRateEntityToRateDto( rateEntity ) );
        }

        return list;
    }

    @Override
    public RateDto fromRateRequestToRateDto(RateRequest rateRequest) {
        if ( rateRequest == null ) {
            return null;
        }

        RateDto.RateDtoBuilder rateDto = RateDto.builder();

        rateDto.userId( rateRequest.getUserId() );
        rateDto.mangaId( rateRequest.getMangaId() );
        rateDto.rate( rateRequest.getRate() );
        rateDto.comment( rateRequest.getComment() );
        rateDto.date( rateRequest.getDate() );

        return rateDto.build();
    }

    @Override
    public RateResponse fromRateDtoToRateResponse(RateDto rateDto) {
        if ( rateDto == null ) {
            return null;
        }

        RateResponse.RateResponseBuilder rateResponse = RateResponse.builder();

        rateResponse.id( rateDto.getId() );
        rateResponse.userId( rateDto.getUserId() );
        rateResponse.mangaId( rateDto.getMangaId() );
        rateResponse.rate( rateDto.getRate() );
        rateResponse.comment( rateDto.getComment() );
        rateResponse.date( rateDto.getDate() );

        return rateResponse.build();
    }

    @Override
    public List<RateResponse> fromRateDtoListToRateResponseList(List<RateDto> rateDtoList) {
        if ( rateDtoList == null ) {
            return null;
        }

        List<RateResponse> list = new ArrayList<RateResponse>( rateDtoList.size() );
        for ( RateDto rateDto : rateDtoList ) {
            list.add( fromRateDtoToRateResponse( rateDto ) );
        }

        return list;
    }

    @Override
    public RateEntity fromRateDtoToRateEntity(RateDto rateDto) {
        if ( rateDto == null ) {
            return null;
        }

        RateEntity.RateEntityBuilder<?, ?> rateEntity = RateEntity.builder();

        rateEntity.id( rateDto.getId() );
        rateEntity.rate( rateDto.getRate() );
        rateEntity.comment( rateDto.getComment() );
        rateEntity.date( rateDto.getDate() );

        return rateEntity.build();
    }
}
