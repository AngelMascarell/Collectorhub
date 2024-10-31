package com.collectorhub.collectorhub.dto.mappers;

import com.collectorhub.collectorhub.controller.request.MangaRequest;
import com.collectorhub.collectorhub.controller.response.MangaResponse;
import com.collectorhub.collectorhub.dto.MangaDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-31T13:36:01+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class AbstractMangaDtoMapperImpl implements AbstractMangaDtoMapper {

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
        mangaDto.imageUrl( mangaRequest.getImageUrl() );

        return mangaDto.build();
    }

    @Override
    public MangaResponse fromMangaDtoToMangaResponse(MangaDto manga) {
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
        mangaResponse.imageUrl( manga.getImageUrl() );

        return mangaResponse.build();
    }

    @Override
    public List<MangaResponse> fromMangaDtoListToMangaResponseList(List<MangaDto> mangaDtoList) {
        if ( mangaDtoList == null ) {
            return null;
        }

        List<MangaResponse> list = new ArrayList<MangaResponse>( mangaDtoList.size() );
        for ( MangaDto mangaDto : mangaDtoList ) {
            list.add( fromMangaDtoToMangaResponse( mangaDto ) );
        }

        return list;
    }
}
