package com.collectorhub.collectorhub.services.impl;

import com.collectorhub.collectorhub.database.entities.UserEntity;
import com.collectorhub.collectorhub.database.repositories.UserRepository;
import com.collectorhub.collectorhub.dto.UserDto;
import com.collectorhub.collectorhub.dto.mappers.AbstractUserDtoMapper;
import com.collectorhub.collectorhub.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AbstractUserDtoMapper userDtoMapper;

    @Override
    public UserDto findByUsername(String username) {
        return userDtoMapper.fromUserEntityToUserDto(userRepository.findByUsername(username));
    }

    @Override
    public UserDto findByEmail(String email) {
        return userDtoMapper.fromUserEntityToUserDto(userRepository.findByEmail(email));
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        if (userRepository.findByUsername(userDto.getUsername()) != null) {
            throw new IllegalArgumentException("El nombre de usuario ya está en uso.");
        }

        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new IllegalArgumentException("El correo electrónico ya está en uso.");
        }

        UserEntity userEntity = userDtoMapper.fromUserDtoToUserEntity(userDto);

        UserEntity savedUserEntity = userRepository.save(userEntity);

        return userDtoMapper.fromUserEntityToUserDto(savedUserEntity);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();

        // Mapea cada UserEntity a UserDto utilizando el mapper
        return userEntities.stream()
                .map(userDtoMapper::fromUserEntityToUserDto) // Cambia aquí para mapear individualmente
                .collect(Collectors.toList()); // Utiliza collect para convertir a lista
    }




}
