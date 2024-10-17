package com.collectorhub.collectorhub.services;

import com.collectorhub.collectorhub.database.entities.UserEntity;
import com.collectorhub.collectorhub.dto.UserDto;

import java.util.List;

public interface UserService {

    public UserDto findByUsername(String username);
    public UserDto findById(long id);

    public UserDto findByEmail(String email);

    public UserDto createUser(UserDto userDto);

    public List<UserDto> getAllUsers();

    public long countAllUsers();

    public long countUsersSubscribed();
}
