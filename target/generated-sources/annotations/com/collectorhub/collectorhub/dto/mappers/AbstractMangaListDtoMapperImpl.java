package com.collectorhub.collectorhub.dto.mappers;

import com.collectorhub.collectorhub.controller.response.MangaListResponse;
import com.collectorhub.collectorhub.database.entities.MangaListEntity;
import com.collectorhub.collectorhub.dto.MangaListDto;
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
public class AbstractMangaListDtoMapperImpl implements AbstractMangaListDtoMapper {

    @Override
    public List<MangaListDto> fromMangaListEntityListToMangaListDtoList(List<MangaListEntity> mangaListEntityList) {
        if ( mangaListEntityList == null ) {
            return null;
        }

        List<MangaListDto> list = new ArrayList<MangaListDto>( mangaListEntityList.size() );
        for ( MangaListEntity mangaListEntity : mangaListEntityList ) {
            list.add( fromMangaListEntityToMangaListDto( mangaListEntity ) );
        }

        return list;
    }

    @Override
    public MangaListResponse fromMangaListDtoToMangaListResponse(MangaListDto dto) {
        if ( dto == null ) {
            return null;
        }

        MangaListResponse.MangaListResponseBuilder mangaListResponse = MangaListResponse.builder();

        mangaListResponse.description( dto.getDescription() );

        return mangaListResponse.build();
    }

    @Override
    public List<MangaListResponse> fromMangaListDtoListToMangaListResponseList(List<MangaListDto> mangaListDtoList) {
        if ( mangaListDtoList == null ) {
            return null;
        }

        List<MangaListResponse> list = new ArrayList<MangaListResponse>( mangaListDtoList.size() );
        for ( MangaListDto mangaListDto : mangaListDtoList ) {
            list.add( fromMangaListDtoToMangaListResponse( mangaListDto ) );
        }

        return list;
    }
}
