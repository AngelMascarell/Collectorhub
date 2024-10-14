package com.collectorhub.collectorhub.dto.mappers;

import com.collectorhub.collectorhub.controller.request.UserRequest;
import com.collectorhub.collectorhub.controller.response.UserResponse;
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
    date = "2024-10-14T16:49:08+0200",
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
        userDto.password( userEntity.getPassword() );
        userDto.birthdate( userEntity.getBirthdate() );
        userDto.registerDate( userEntity.getRegisterDate() );
        userDto.mangas( mangaEntityListToMangaDtoList( userEntity.getMangas() ) );

        return userDto.build();
    }

    @Override
    public UserDto fromUserRequestToUserDto(UserRequest userRequest) {
        if ( userRequest == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.username( userRequest.getUsername() );
        userDto.email( userRequest.getEmail() );
        userDto.birthdate( userRequest.getBirthdate() );
        userDto.registerDate( userRequest.getRegisterDate() );
        List<MangaDto> list = userRequest.getMangas();
        if ( list != null ) {
            userDto.mangas( new ArrayList<MangaDto>( list ) );
        }

        return userDto.build();
    }

    @Override
    public UserResponse fromUserDtoToUserResponse(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.id( userDto.getId() );
        userResponse.username( userDto.getUsername() );
        userResponse.email( userDto.getEmail() );
        userResponse.birthdate( userDto.getBirthdate() );
        userResponse.registerDate( userDto.getRegisterDate() );
        List<MangaDto> list = userDto.getMangas();
        if ( list != null ) {
            userResponse.mangas( new ArrayList<MangaDto>( list ) );
        }

        return userResponse.build();
    }

    @Override
    public UserEntity fromUserDtoToUserEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder<?, ?> userEntity = UserEntity.builder();

        userEntity.id( userDto.getId() );
        userEntity.username( userDto.getUsername() );
        userEntity.email( userDto.getEmail() );
        userEntity.password( userDto.getPassword() );
        userEntity.birthdate( userDto.getBirthdate() );
        userEntity.registerDate( userDto.getRegisterDate() );
        userEntity.mangas( mangaDtoListToMangaEntityList( userDto.getMangas() ) );

        return userEntity.build();
    }

    @Override
    public List<UserResponse> fromUserDtoListToUserResponseList(List<UserDto> userDtoList) {
        if ( userDtoList == null ) {
            return null;
        }

        List<UserResponse> list = new ArrayList<UserResponse>( userDtoList.size() );
        for ( UserDto userDto : userDtoList ) {
            list.add( fromUserDtoToUserResponse( userDto ) );
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

    protected MangaEntity mangaDtoToMangaEntity(MangaDto mangaDto) {
        if ( mangaDto == null ) {
            return null;
        }

        MangaEntity.MangaEntityBuilder<?, ?> mangaEntity = MangaEntity.builder();

        mangaEntity.id( mangaDto.getId() );
        mangaEntity.title( mangaDto.getTitle() );
        mangaEntity.author( mangaDto.getAuthor() );
        mangaEntity.chapters( mangaDto.getChapters() );
        mangaEntity.completed( mangaDto.isCompleted() );

        return mangaEntity.build();
    }

    protected List<MangaEntity> mangaDtoListToMangaEntityList(List<MangaDto> list) {
        if ( list == null ) {
            return null;
        }

        List<MangaEntity> list1 = new ArrayList<MangaEntity>( list.size() );
        for ( MangaDto mangaDto : list ) {
            list1.add( mangaDtoToMangaEntity( mangaDto ) );
        }

        return list1;
    }
}
