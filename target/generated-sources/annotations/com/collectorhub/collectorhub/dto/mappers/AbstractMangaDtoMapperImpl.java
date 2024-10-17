package com.collectorhub.collectorhub.dto.mappers;

import com.collectorhub.collectorhub.controller.request.MangaRequest;
import com.collectorhub.collectorhub.controller.response.MangaResponse;
import com.collectorhub.collectorhub.database.entities.MangaEntity;
import com.collectorhub.collectorhub.dto.MangaDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-17T14:40:40+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class AbstractMangaDtoMapperImpl implements AbstractMangaDtoMapper {

    @Override
    public MangaDto fromMangaEntityToMangaDto(MangaEntity mangaEntity) {
        if ( mangaEntity == null ) {
            return null;
        }

        MangaDto.MangaDtoBuilder mangaDto = MangaDto.builder();

        mangaDto.id( mangaEntity.getId() );
        mangaDto.title( mangaEntity.getTitle() );
        mangaDto.author( mangaEntity.getAuthor() );
        mangaDto.chapters( mangaEntity.getChapters() );
        mangaDto.completed( mangaEntity.isCompleted() );

        return mangaDto.build();
    }

    @Override
    public List<MangaDto> fromMangaEntityListToMangaDtoList(List<MangaEntity> mangaEntityList) {
        if ( mangaEntityList == null ) {
            return null;
        }

        List<MangaDto> list = new ArrayList<MangaDto>( mangaEntityList.size() );
        for ( MangaEntity mangaEntity : mangaEntityList ) {
            list.add( fromMangaEntityToMangaDto( mangaEntity ) );
        }

        return list;
    }

    @Override
    public MangaDto fromMangaRequestToMangaDto(MangaRequest mangaRequest) {
        if ( mangaRequest == null ) {
            return null;
        }

        MangaDto.MangaDtoBuilder mangaDto = MangaDto.builder();

        mangaDto.title( mangaRequest.getTitle() );
        mangaDto.author( mangaRequest.getAuthor() );
        mangaDto.genreId( mangaRequest.getGenreId() );
        mangaDto.chapters( mangaRequest.getChapters() );
        mangaDto.completed( mangaRequest.isCompleted() );

        return mangaDto.build();
    }

    @Override
    public MangaResponse fromMangaDtotoMangaResponse(MangaDto manga) {
        if ( manga == null ) {
            return null;
        }

        MangaResponse.MangaResponseBuilder mangaResponse = MangaResponse.builder();

        mangaResponse.id( manga.getId() );
        mangaResponse.title( manga.getTitle() );
        mangaResponse.author( manga.getAuthor() );
        mangaResponse.genreId( manga.getGenreId() );
        mangaResponse.chapters( manga.getChapters() );
        mangaResponse.completed( manga.isCompleted() );

        return mangaResponse.build();
    }

    @Override
    public List<MangaResponse> fromMangaDtoListToMangaResponseList(List<MangaDto> mangaDtoList) {
        if ( mangaDtoList == null ) {
            return null;
        }

        List<MangaResponse> list = new ArrayList<MangaResponse>( mangaDtoList.size() );
        for ( MangaDto mangaDto : mangaDtoList ) {
            list.add( fromMangaDtotoMangaResponse( mangaDto ) );
        }

        return list;
    }
}
