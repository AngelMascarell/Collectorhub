package com.collectorhub.collectorhub.services.impl;

import com.collectorhub.collectorhub.controller.request.UserFilterRequest;
import com.collectorhub.collectorhub.database.entities.UserEntity;
import com.collectorhub.collectorhub.database.repositories.UserRepository;
import com.collectorhub.collectorhub.dto.UserDto;
import com.collectorhub.collectorhub.dto.mappers.AbstractUserDtoMapper;
import com.collectorhub.collectorhub.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AbstractUserDtoMapper userDtoMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDto findByUsername(String username) {
        return userDtoMapper.fromUserEntityToUserDto(userRepository.findByUsername(username));
    }

    @Override
    public UserDto findById(long id) {
        return userDtoMapper.fromUserEntityToUserDto(userRepository.findById(id));
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

        return userEntities.stream()
                .map(userDtoMapper::fromUserEntityToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public long countAllUsers() {
        return userRepository.count();
    }

    @Override
    public long countUsersSubscribed() {
        List<UserEntity> totalUsers = userRepository.findAll();
        long usersSubscribed = 0;
        for(UserEntity user: totalUsers) {
            if (user.isPremium()) {
                usersSubscribed += 1;
            }
        }
        return usersSubscribed;
    }

    @Override
    public UserDto updateUser(UserDto userDto, long id) {
        UserEntity existingUser = userRepository.findById(id);

        if (userDto.getUsername() != null) {
            existingUser.setUsername(userDto.getUsername());
        }

        if (userDto.getEmail() != null) {
            existingUser.setEmail(userDto.getEmail());
        }

        if (userDto.getPassword() != null) {
            existingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }


        if (userDto.getBirthdate() != null) {
            existingUser.setBirthdate(userDto.getBirthdate());
        }

        // No sobreescribimos otros campos como "premium" o "fecha de registro" ya que no se modifican

        UserEntity updatedUser = userRepository.save(existingUser);

        return userDtoMapper.fromUserEntityToUserDto(updatedUser);
    }

    public List<UserDto> filterUsers(UserFilterRequest filter) {
        if (filter == null) {
            throw new IllegalArgumentException("El filtro no puede ser nulo.");
        }

        List<UserEntity> allUsers = userRepository.findAll();
        List<UserDto> allDtoUsers = userDtoMapper.fromUserEntityListToUserDtoList(allUsers);

        System.out.println("Número de usuarios encontrados: " + allUsers.size());

        List<UserDto> filteredUsers = allDtoUsers.stream()
                .filter(user ->
                        // Comprobación del nombre de usuario
                        (filter.getUsername() != null && user.getUsername() != null && user.getUsername().contains(filter.getUsername())) ||

                                // Comprobación del correo electrónico
                                (filter.getEmail() != null && user.getEmail() != null && user.getEmail().contains(filter.getEmail())) ||

                                // Comprobación de la fecha de registro
                                (filter.getRegisterDate() != null && user.getRegisterDate() != null && !user.getRegisterDate().isBefore(filter.getRegisterDate())) ||

                                // Comprobación de la fecha de nacimiento
                                (filter.getBirthdate() != null && user.getBirthdate() != null && !user.getBirthdate().isBefore(filter.getBirthdate())) ||

                                // Comprobación del rol
                                (filter.getRole() != null && user.getRole() != null && user.getRole().getName().equals(filter.getRole()))
                )
                .collect(Collectors.toList());

        return filteredUsers;
    }








}
