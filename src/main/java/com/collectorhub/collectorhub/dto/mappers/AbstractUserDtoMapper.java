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
        List<UserDto> userDtoList = new ArrayList<>();

        for (UserEntity userEntity : userEntityList) {
            UserDto userDto = fromUserEntityToUserDto(userEntity);
            userDto.setId(userEntity.getId());
            userDtoList.add(userDto);
        }

        return userDtoList;
    }


    UserDto fromUserRequestToUserDto(UserRequest userRequest);

    default UserResponse fromUserDtoToUserResponse(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        UserResponse userResponse = new UserResponse();
        userResponse.setId(userDto.getId());
        userResponse.setUsername(userDto.getUsername());
        userResponse.setEmail(userDto.getEmail());
        userResponse.setBirthdate(userDto.getBirthdate());
        userResponse.setRegisterDate(userDto.getRegisterDate());
        userResponse.setPremium(userDto.isPremium());

        userResponse.setPremiumStartDate(userDto.getPremiumStartDate() != null ? userDto.getPremiumStartDate() : null);
        userResponse.setPremiumEndDate(userDto.getPremiumEndDate() != null ? userDto.getPremiumEndDate() : null);

        if (userDto.getRole() != null) {
            userResponse.setRole(userDto.getRole());
        }

        return userResponse;
    }

    UserEntity fromUserDtoToUserEntity(UserDto userDto);

    List<UserResponse> fromUserDtoListToUserResponseList(List<UserDto> userDtoList);

}
