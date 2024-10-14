package com.collectorhub.collectorhub.dto.mappers;

import com.collectorhub.collectorhub.controller.request.UserRequest;
import com.collectorhub.collectorhub.controller.response.UserResponse;
import com.collectorhub.collectorhub.database.entities.GenreEntity;
import com.collectorhub.collectorhub.database.entities.UserEntity;
import com.collectorhub.collectorhub.dto.GenreDto;
import com.collectorhub.collectorhub.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AbstractUserDtoMapper {

    UserDto fromUserEntityToUserDto(UserEntity userEntity);

    default List<UserDto> fromUserEntityListToUserDtoList(List<UserEntity> userEntityList) {
        List<UserDto> userDtoList = new ArrayList<>();  // Crea una lista para almacenar los DTOs

        for (UserEntity userEntity : userEntityList) {
            UserDto userDto = fromUserEntityToUserDto(userEntity);  // Convierte la entidad a DTO
            userDto.setId(userEntity.getId());  // Asegúrate de que esto no esté nulo
            userDtoList.add(userDto);  // Agrega el DTO a la lista
        }

        return userDtoList;  // Devuelve la lista completa
    }


    UserDto fromUserRequestToUserDto(UserRequest userRequest);

    UserResponse fromUserDtoToUserResponse(UserDto userDto);

    UserEntity fromUserDtoToUserEntity(UserDto userDto);

    List<UserResponse> fromUserDtoListToUserResponseList(List<UserDto> userDtoList);

}
