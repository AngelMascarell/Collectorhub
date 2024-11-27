package com.collectorhub.collectorhub.services;

import com.collectorhub.collectorhub.controller.request.UserFilterRequest;
import com.collectorhub.collectorhub.database.entities.UserEntity;
import com.collectorhub.collectorhub.dto.MangaDto;
import com.collectorhub.collectorhub.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    public UserDto findByUsername(String username);
    public UserDto findById(long id);

    public UserDto findByEmail(String email);

    public UserDto createUser(UserDto userDto);

    public List<UserDto> getAllUsers();

    public long countAllUsers();

    public long countUsersSubscribed();

    public UserDto updateUser(UserDto userDto, long id);

    public List<UserDto> filterUsers(UserFilterRequest filter);

    public ResponseEntity<String> addMangaToUser(UserEntity user, Long mangaId);

    List<MangaDto> getUserMangas(Long userId);
}
