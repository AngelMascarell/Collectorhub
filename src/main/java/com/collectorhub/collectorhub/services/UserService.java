package com.collectorhub.collectorhub.services;

import com.collectorhub.collectorhub.controller.request.UserFilterRequest;
import com.collectorhub.collectorhub.database.entities.UserEntity;
import com.collectorhub.collectorhub.dto.MangaDto;
import com.collectorhub.collectorhub.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    public UserDto findByUsername(String username);

    public UserDto findById(long id);

    public UserDto findByEmail(String email);

    public UserDto createUser(UserDto userDto);
    public UserDto createAdminUser(UserDto userDto);

    public List<UserDto> getAllUsers();

    public long countAllUsers();

    public long countUsersSubscribed();

    public UserDto updateUser(UserDto userDto, long id);

    public List<UserDto> filterUsers(UserFilterRequest filter);

    public ResponseEntity<String> addMangaToUser(UserEntity user, Long mangaId);

    public ResponseEntity<String> addDesiredMangaToUser(UserEntity user, Long mangaId);

    public List<MangaDto> getUserMangas(Long userId);

    public List<MangaDto> getUserDesiredMangas(Long userId);
}
