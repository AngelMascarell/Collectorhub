package com.collectorhub.collectorhub.services;

import com.collectorhub.collectorhub.database.entities.UserEntity;
import com.collectorhub.collectorhub.dto.UserDto;

public interface UserService {

    public UserDto findByUsername(String username);

    public UserDto findByEmail(String email);
}
