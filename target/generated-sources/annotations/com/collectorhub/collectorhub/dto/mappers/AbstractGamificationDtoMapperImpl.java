package com.collectorhub.collectorhub.dto.mappers;

import com.collectorhub.collectorhub.controller.request.GamificationRequest;
import com.collectorhub.collectorhub.controller.response.GamificationResponse;
import com.collectorhub.collectorhub.database.entities.GamificationConditionEntity;
import com.collectorhub.collectorhub.database.entities.GamificationEntity;
import com.collectorhub.collectorhub.dto.GamificationConditionDto;
import com.collectorhub.collectorhub.dto.GamificationDto;
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
public class AbstractGamificationDtoMapperImpl implements AbstractGamificationDtoMapper {

    @Override
    public GamificationDto fromGamificationEntityToGamificationDto(GamificationEntity gamificationEntity) {
        if ( gamificationEntity == null ) {
            return null;
        }

        GamificationDto.GamificationDtoBuilder gamificationDto = GamificationDto.builder();

        gamificationDto.id( gamificationEntity.getId() );
        gamificationDto.title( gamificationEntity.getTitle() );
        gamificationDto.description( gamificationEntity.getDescription() );
        gamificationDto.imageUrl( gamificationEntity.getImageUrl() );
        gamificationDto.conditions( gamificationConditionEntityListToGamificationConditionDtoList( gamificationEntity.getConditions() ) );
        gamificationDto.createdAt( gamificationEntity.getCreatedAt() );
        gamificationDto.updatedAt( gamificationEntity.getUpdatedAt() );

        return gamificationDto.build();
    }

    @Override
    public List<GamificationDto> fromGamificationEntityListToGamificationDtoList(List<GamificationEntity> gamificationEntityList) {
        if ( gamificationEntityList == null ) {
            return null;
        }

        List<GamificationDto> list = new ArrayList<GamificationDto>( gamificationEntityList.size() );
        for ( GamificationEntity gamificationEntity : gamificationEntityList ) {
            list.add( fromGamificationEntityToGamificationDto( gamificationEntity ) );
        }

        return list;
    }

    @Override
    public GamificationDto fromGamificationRequestToGamificationDto(GamificationRequest gamificationRequest) {
        if ( gamificationRequest == null ) {
            return null;
        }

        GamificationDto.GamificationDtoBuilder gamificationDto = GamificationDto.builder();

        gamificationDto.title( gamificationRequest.getTitle() );
        gamificationDto.description( gamificationRequest.getDescription() );
        gamificationDto.imageUrl( gamificationRequest.getImageUrl() );
        List<GamificationConditionDto> list = gamificationRequest.getConditions();
        if ( list != null ) {
            gamificationDto.conditions( new ArrayList<GamificationConditionDto>( list ) );
        }

        return gamificationDto.build();
    }

    @Override
    public GamificationResponse fromGamificationDtoToGamificationResponse(GamificationDto gamificationDto) {
        if ( gamificationDto == null ) {
            return null;
        }

        GamificationResponse.GamificationResponseBuilder gamificationResponse = GamificationResponse.builder();

        gamificationResponse.id( gamificationDto.getId() );
        gamificationResponse.title( gamificationDto.getTitle() );
        gamificationResponse.description( gamificationDto.getDescription() );
        gamificationResponse.imageUrl( gamificationDto.getImageUrl() );
        List<GamificationConditionDto> list = gamificationDto.getConditions();
        if ( list != null ) {
            gamificationResponse.conditions( new ArrayList<GamificationConditionDto>( list ) );
        }

        return gamificationResponse.build();
    }

    @Override
    public List<GamificationResponse> fromGamificationDtoListToGamificationResponseList(List<GamificationDto> gamificationDtoList) {
        if ( gamificationDtoList == null ) {
            return null;
        }

        List<GamificationResponse> list = new ArrayList<GamificationResponse>( gamificationDtoList.size() );
        for ( GamificationDto gamificationDto : gamificationDtoList ) {
            list.add( fromGamificationDtoToGamificationResponse( gamificationDto ) );
        }

        return list;
    }

    @Override
    public GamificationEntity fromGamificationDtoToGamificationEntity(GamificationDto gamificationDto) {
        if ( gamificationDto == null ) {
            return null;
        }

        GamificationEntity.GamificationEntityBuilder<?, ?> gamificationEntity = GamificationEntity.builder();

        gamificationEntity.id( gamificationDto.getId() );
        gamificationEntity.title( gamificationDto.getTitle() );
        gamificationEntity.description( gamificationDto.getDescription() );
        gamificationEntity.imageUrl( gamificationDto.getImageUrl() );
        gamificationEntity.createdAt( gamificationDto.getCreatedAt() );
        gamificationEntity.updatedAt( gamificationDto.getUpdatedAt() );
        gamificationEntity.conditions( gamificationConditionDtoListToGamificationConditionEntityList( gamificationDto.getConditions() ) );

        return gamificationEntity.build();
    }

    @Override
    public GamificationResponse fromGamificationRequestToGamificationResponse(GamificationRequest gamificationRequest) {
        if ( gamificationRequest == null ) {
            return null;
        }

        GamificationResponse.GamificationResponseBuilder gamificationResponse = GamificationResponse.builder();

        gamificationResponse.title( gamificationRequest.getTitle() );
        gamificationResponse.description( gamificationRequest.getDescription() );
        gamificationResponse.imageUrl( gamificationRequest.getImageUrl() );
        List<GamificationConditionDto> list = gamificationRequest.getConditions();
        if ( list != null ) {
            gamificationResponse.conditions( new ArrayList<GamificationConditionDto>( list ) );
        }

        return gamificationResponse.build();
    }

    protected GamificationConditionDto gamificationConditionEntityToGamificationConditionDto(GamificationConditionEntity gamificationConditionEntity) {
        if ( gamificationConditionEntity == null ) {
            return null;
        }

        GamificationConditionDto.GamificationConditionDtoBuilder gamificationConditionDto = GamificationConditionDto.builder();

        gamificationConditionDto.id( gamificationConditionEntity.getId() );
        gamificationConditionDto.type( gamificationConditionEntity.getType() );
        gamificationConditionDto.threshold( gamificationConditionEntity.getThreshold() );

        return gamificationConditionDto.build();
    }

    protected List<GamificationConditionDto> gamificationConditionEntityListToGamificationConditionDtoList(List<GamificationConditionEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<GamificationConditionDto> list1 = new ArrayList<GamificationConditionDto>( list.size() );
        for ( GamificationConditionEntity gamificationConditionEntity : list ) {
            list1.add( gamificationConditionEntityToGamificationConditionDto( gamificationConditionEntity ) );
        }

        return list1;
    }

    protected GamificationConditionEntity gamificationConditionDtoToGamificationConditionEntity(GamificationConditionDto gamificationConditionDto) {
        if ( gamificationConditionDto == null ) {
            return null;
        }

        GamificationConditionEntity.GamificationConditionEntityBuilder<?, ?> gamificationConditionEntity = GamificationConditionEntity.builder();

        gamificationConditionEntity.id( gamificationConditionDto.getId() );
        gamificationConditionEntity.type( gamificationConditionDto.getType() );
        gamificationConditionEntity.threshold( gamificationConditionDto.getThreshold() );

        return gamificationConditionEntity.build();
    }

    protected List<GamificationConditionEntity> gamificationConditionDtoListToGamificationConditionEntityList(List<GamificationConditionDto> list) {
        if ( list == null ) {
            return null;
        }

        List<GamificationConditionEntity> list1 = new ArrayList<GamificationConditionEntity>( list.size() );
        for ( GamificationConditionDto gamificationConditionDto : list ) {
            list1.add( gamificationConditionDtoToGamificationConditionEntity( gamificationConditionDto ) );
        }

        return list1;
    }
}
