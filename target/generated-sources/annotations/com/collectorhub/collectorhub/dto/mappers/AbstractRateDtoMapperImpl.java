package com.collectorhub.collectorhub.dto.mappers;

import com.collectorhub.collectorhub.database.entities.RateEntity;
import com.collectorhub.collectorhub.dto.RateDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-30T11:59:13+0200",
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
}
