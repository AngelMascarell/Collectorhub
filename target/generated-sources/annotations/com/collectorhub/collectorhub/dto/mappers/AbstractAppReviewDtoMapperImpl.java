package com.collectorhub.collectorhub.dto.mappers;

import com.collectorhub.collectorhub.controller.request.AppReviewRequest;
import com.collectorhub.collectorhub.controller.response.AppReviewResponse;
import com.collectorhub.collectorhub.database.entities.AppReviewEntity;
import com.collectorhub.collectorhub.dto.AppReviewDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-28T13:06:23+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class AbstractAppReviewDtoMapperImpl implements AbstractAppReviewDtoMapper {

    @Override
    public AppReviewDto fromAppReviewEntityToAppReviewDto(AppReviewEntity AppReviewEntity) {
        if ( AppReviewEntity == null ) {
            return null;
        }

        AppReviewDto.AppReviewDtoBuilder appReviewDto = AppReviewDto.builder();

        appReviewDto.id( AppReviewEntity.getId() );
        appReviewDto.rate( AppReviewEntity.getRate() );
        appReviewDto.comment( AppReviewEntity.getComment() );
        appReviewDto.date( AppReviewEntity.getDate() );

        return appReviewDto.build();
    }

    @Override
    public AppReviewDto fromAppReviewRequestToAppReviewDto(AppReviewRequest AppReviewRequest) {
        if ( AppReviewRequest == null ) {
            return null;
        }

        AppReviewDto.AppReviewDtoBuilder appReviewDto = AppReviewDto.builder();

        appReviewDto.userId( AppReviewRequest.getUserId() );
        appReviewDto.rate( AppReviewRequest.getRate() );
        appReviewDto.comment( AppReviewRequest.getComment() );
        appReviewDto.date( AppReviewRequest.getDate() );

        return appReviewDto.build();
    }

    @Override
    public AppReviewResponse fromAppReviewDtoToAppReviewResponse(AppReviewDto AppReviewDto) {
        if ( AppReviewDto == null ) {
            return null;
        }

        AppReviewResponse.AppReviewResponseBuilder appReviewResponse = AppReviewResponse.builder();

        appReviewResponse.id( AppReviewDto.getId() );
        appReviewResponse.userId( AppReviewDto.getUserId() );
        appReviewResponse.rate( AppReviewDto.getRate() );
        appReviewResponse.comment( AppReviewDto.getComment() );
        appReviewResponse.date( AppReviewDto.getDate() );

        return appReviewResponse.build();
    }

    @Override
    public List<AppReviewResponse> fromAppReviewDtoListToAppReviewResponseList(List<AppReviewDto> AppReviewDtoList) {
        if ( AppReviewDtoList == null ) {
            return null;
        }

        List<AppReviewResponse> list = new ArrayList<AppReviewResponse>( AppReviewDtoList.size() );
        for ( AppReviewDto appReviewDto : AppReviewDtoList ) {
            list.add( fromAppReviewDtoToAppReviewResponse( appReviewDto ) );
        }

        return list;
    }

    @Override
    public AppReviewEntity fromAppReviewDtoToAppReviewEntity(AppReviewDto AppReviewDto) {
        if ( AppReviewDto == null ) {
            return null;
        }

        AppReviewEntity.AppReviewEntityBuilder<?, ?> appReviewEntity = AppReviewEntity.builder();

        appReviewEntity.id( AppReviewDto.getId() );
        appReviewEntity.rate( AppReviewDto.getRate() );
        appReviewEntity.comment( AppReviewDto.getComment() );
        appReviewEntity.date( AppReviewDto.getDate() );

        return appReviewEntity.build();
    }
}
