package com.collectorhub.collectorhub.dto.mappers;

import com.collectorhub.collectorhub.controller.request.GamificationConditionRequest;
import com.collectorhub.collectorhub.controller.response.GamificationConditionResponse;
import com.collectorhub.collectorhub.database.entities.GamificationConditionEntity;
import com.collectorhub.collectorhub.dto.GamificationConditionDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-04T17:15:40+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class AbstractGamificationConditionDtoMapperImpl implements AbstractGamificationConditionDtoMapper {

    @Override
    public GamificationConditionDto fromGamificationConditionEntityToGamificationConditionDto(GamificationConditionEntity gamificationConditionEntity) {
        if ( gamificationConditionEntity == null ) {
            return null;
        }

        GamificationConditionDto.GamificationConditionDtoBuilder gamificationConditionDto = GamificationConditionDto.builder();

        gamificationConditionDto.id( gamificationConditionEntity.getId() );
        gamificationConditionDto.type( gamificationConditionEntity.getType() );
        gamificationConditionDto.threshold( gamificationConditionEntity.getThreshold() );

        return gamificationConditionDto.build();
    }

    @Override
    public List<GamificationConditionDto> fromGamificationConditionEntityListToGamificationConditionDtoList(List<GamificationConditionEntity> gamificationConditionEntityList) {
        if ( gamificationConditionEntityList == null ) {
            return null;
        }

        List<GamificationConditionDto> list = new ArrayList<GamificationConditionDto>( gamificationConditionEntityList.size() );
        for ( GamificationConditionEntity gamificationConditionEntity : gamificationConditionEntityList ) {
            list.add( fromGamificationConditionEntityToGamificationConditionDto( gamificationConditionEntity ) );
        }

        return list;
    }

    @Override
    public GamificationConditionDto fromGamificationConditionRequestToGamificationConditionDto(GamificationConditionRequest gamificationConditionRequest) {
        if ( gamificationConditionRequest == null ) {
            return null;
        }

        GamificationConditionDto.GamificationConditionDtoBuilder gamificationConditionDto = GamificationConditionDto.builder();

        gamificationConditionDto.type( gamificationConditionRequest.getType() );
        gamificationConditionDto.threshold( gamificationConditionRequest.getThreshold() );

        return gamificationConditionDto.build();
    }

    @Override
    public GamificationConditionResponse fromGamificationConditionDtoToGamificationConditionResponse(GamificationConditionDto gamificationConditionDto) {
        if ( gamificationConditionDto == null ) {
            return null;
        }

        GamificationConditionResponse.GamificationConditionResponseBuilder gamificationConditionResponse = GamificationConditionResponse.builder();

        gamificationConditionResponse.id( gamificationConditionDto.getId() );
        gamificationConditionResponse.type( gamificationConditionDto.getType() );
        gamificationConditionResponse.threshold( gamificationConditionDto.getThreshold() );

        return gamificationConditionResponse.build();
    }

    @Override
    public List<GamificationConditionResponse> fromGamificationConditionDtoListToGamificationConditionResponseList(List<GamificationConditionDto> gamificationConditionDtoList) {
        if ( gamificationConditionDtoList == null ) {
            return null;
        }

        List<GamificationConditionResponse> list = new ArrayList<GamificationConditionResponse>( gamificationConditionDtoList.size() );
        for ( GamificationConditionDto gamificationConditionDto : gamificationConditionDtoList ) {
            list.add( fromGamificationConditionDtoToGamificationConditionResponse( gamificationConditionDto ) );
        }

        return list;
    }

    @Override
    public GamificationConditionEntity fromGamificationConditionDtoToGamificationConditionEntity(GamificationConditionDto gamificationConditionDto) {
        if ( gamificationConditionDto == null ) {
            return null;
        }

        GamificationConditionEntity.GamificationConditionEntityBuilder<?, ?> gamificationConditionEntity = GamificationConditionEntity.builder();

        gamificationConditionEntity.id( gamificationConditionDto.getId() );
        gamificationConditionEntity.type( gamificationConditionDto.getType() );
        gamificationConditionEntity.threshold( gamificationConditionDto.getThreshold() );

        return gamificationConditionEntity.build();
    }
}
