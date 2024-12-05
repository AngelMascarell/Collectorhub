package com.collectorhub.collectorhub.dto.mappers;

import com.collectorhub.collectorhub.controller.request.GenreRequest;
import com.collectorhub.collectorhub.controller.response.GenreResponse;
import com.collectorhub.collectorhub.database.entities.GenreEntity;
import com.collectorhub.collectorhub.dto.GenreDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-05T15:35:25+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class AbstractGenreDtoMapperImpl implements AbstractGenreDtoMapper {

    @Override
    public GenreDto fromGenreEntityToGenreDto(GenreEntity genreEntity) {
        if ( genreEntity == null ) {
            return null;
        }

        GenreDto.GenreDtoBuilder genreDto = GenreDto.builder();

        genreDto.id( genreEntity.getId() );
        genreDto.name( genreEntity.getName() );

        return genreDto.build();
    }

    @Override
    public List<GenreDto> fromGenreEntityListToGenreDtoList(List<GenreEntity> genreEntityList) {
        if ( genreEntityList == null ) {
            return null;
        }

        List<GenreDto> list = new ArrayList<GenreDto>( genreEntityList.size() );
        for ( GenreEntity genreEntity : genreEntityList ) {
            list.add( fromGenreEntityToGenreDto( genreEntity ) );
        }

        return list;
    }

    @Override
    public GenreResponse fromGenreDtoToGenreResponse(GenreDto dto) {
        if ( dto == null ) {
            return null;
        }

        GenreResponse.GenreResponseBuilder genreResponse = GenreResponse.builder();

        genreResponse.id( dto.getId() );
        genreResponse.name( dto.getName() );

        return genreResponse.build();
    }

    @Override
    public GenreDto fromGenreRequestToGenreDto(GenreRequest request) {
        if ( request == null ) {
            return null;
        }

        GenreDto.GenreDtoBuilder genreDto = GenreDto.builder();

        genreDto.name( request.getName() );

        return genreDto.build();
    }

    @Override
    public List<GenreResponse> fromGenreDtoListToGenreResponseList(List<GenreDto> genreDtoList) {
        if ( genreDtoList == null ) {
            return null;
        }

        List<GenreResponse> list = new ArrayList<GenreResponse>( genreDtoList.size() );
        for ( GenreDto genreDto : genreDtoList ) {
            list.add( fromGenreDtoToGenreResponse( genreDto ) );
        }

        return list;
    }
}
