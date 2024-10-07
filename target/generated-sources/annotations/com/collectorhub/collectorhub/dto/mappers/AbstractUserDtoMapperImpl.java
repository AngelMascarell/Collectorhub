package com.collectorhub.collectorhub.dto.mappers;

import com.collectorhub.collectorhub.database.entities.MangaEntity;
import com.collectorhub.collectorhub.database.entities.UserEntity;
import com.collectorhub.collectorhub.dto.MangaDto;
import com.collectorhub.collectorhub.dto.UserDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-07T12:12:01+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class AbstractUserDtoMapperImpl implements AbstractUserDtoMapper {

    @Override
    public UserDto fromUserEntityToUserDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( userEntity.getId() );
        userDto.username( userEntity.getUsername() );
        userDto.email( userEntity.getEmail() );
        userDto.birthdate( userEntity.getBirthdate() );
        userDto.registerDate( userEntity.getRegisterDate() );
        userDto.mangas( mangaEntityListToMangaDtoList( userEntity.getMangas() ) );

        return userDto.build();
    }

    @Override
    public List<UserDto> fromUserEntityListToUserDtoList(List<UserEntity> userEntityList) {
        if ( userEntityList == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( userEntityList.size() );
        for ( UserEntity userEntity : userEntityList ) {
            list.add( fromUserEntityToUserDto( userEntity ) );
        }

        return list;
    }

    protected MangaDto mangaEntityToMangaDto(MangaEntity mangaEntity) {
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

    protected List<MangaDto> mangaEntityListToMangaDtoList(List<MangaEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<MangaDto> list1 = new ArrayList<MangaDto>( list.size() );
        for ( MangaEntity mangaEntity : list ) {
            list1.add( mangaEntityToMangaDto( mangaEntity ) );
        }

        return list1;
    }
}
