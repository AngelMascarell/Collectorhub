package com.collectorhub.collectorhub.dto.mappers;

import com.collectorhub.collectorhub.controller.request.UserRequest;
import com.collectorhub.collectorhub.controller.response.UserResponse;
import com.collectorhub.collectorhub.database.entities.MangaEntity;
import com.collectorhub.collectorhub.database.entities.RoleEntity;
import com.collectorhub.collectorhub.database.entities.UserEntity;
import com.collectorhub.collectorhub.dto.MangaDto;
import com.collectorhub.collectorhub.dto.RoleDto;
import com.collectorhub.collectorhub.dto.UserDto;
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
        userDto.desiredMangas( mangaEntityListToMangaDtoList( userEntity.getDesiredMangas() ) );
        userDto.premiumStartDate( userEntity.getPremiumStartDate() );
        userDto.premiumEndDate( userEntity.getPremiumEndDate() );
        userDto.profileImageUrl( userEntity.getProfileImageUrl() );
        userDto.role( roleEntityToRoleDto( userEntity.getRole() ) );

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
        userDto.password( userRequest.getPassword() );
        userDto.birthdate( userRequest.getBirthdate() );
        userDto.registerDate( userRequest.getRegisterDate() );
        List<MangaDto> list = userRequest.getMangas();
        if ( list != null ) {
            userDto.mangas( new ArrayList<MangaDto>( list ) );
        }
        userDto.premiumStartDate( userRequest.getPremiumStartDate() );
        userDto.premiumEndDate( userRequest.getPremiumEndDate() );
        userDto.profileImageUrl( userRequest.getProfileImageUrl() );

        return userDto.build();
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
        userEntity.premiumStartDate( userDto.getPremiumStartDate() );
        userEntity.premiumEndDate( userDto.getPremiumEndDate() );
        userEntity.profileImageUrl( userDto.getProfileImageUrl() );
        userEntity.role( roleDtoToRoleEntity( userDto.getRole() ) );
        userEntity.mangas( mangaDtoListToMangaEntityList( userDto.getMangas() ) );
        userEntity.desiredMangas( mangaDtoListToMangaEntityList( userDto.getDesiredMangas() ) );

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
        mangaDto.imageUrl( mangaEntity.getImageUrl() );
        mangaDto.synopsis( mangaEntity.getSynopsis() );
        mangaDto.releaseDate( mangaEntity.getReleaseDate() );

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

    protected RoleDto roleEntityToRoleDto(RoleEntity roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        RoleDto.RoleDtoBuilder roleDto = RoleDto.builder();

        roleDto.id( roleEntity.getId() );
        roleDto.name( roleEntity.getName() );

        return roleDto.build();
    }

    protected RoleEntity roleDtoToRoleEntity(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setId( roleDto.getId() );
        roleEntity.setName( roleDto.getName() );

        return roleEntity;
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
        mangaEntity.imageUrl( mangaDto.getImageUrl() );
        mangaEntity.synopsis( mangaDto.getSynopsis() );
        mangaEntity.releaseDate( mangaDto.getReleaseDate() );

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
