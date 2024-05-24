package com.collectorhub.collectorhub.dto.mappers;

import com.collectorhub.collectorhub.database.entities.GenreEntity;
import com.collectorhub.collectorhub.database.entities.UserEntity;
import com.collectorhub.collectorhub.dto.GenreDto;
import com.collectorhub.collectorhub.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AbstractUserDtoMapper {

    UserDto fromUserEntityToUserDto(UserEntity userEntity);

    List<UserDto> fromUserEntityListToUserDtoList(List<UserEntity> userEntityList);

}
