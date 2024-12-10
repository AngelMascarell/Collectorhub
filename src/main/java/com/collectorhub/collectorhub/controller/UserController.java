package com.collectorhub.collectorhub.controller;

import com.collectorhub.collectorhub.controller.request.UserFilterRequest;
import com.collectorhub.collectorhub.controller.request.UserRequest;
import com.collectorhub.collectorhub.controller.response.MangaResponseList;
import com.collectorhub.collectorhub.controller.response.UpdateUserResponse;
import com.collectorhub.collectorhub.controller.response.UserResponse;
import com.collectorhub.collectorhub.controller.response.UserResponseList;
import com.collectorhub.collectorhub.database.entities.UserEntity;
import com.collectorhub.collectorhub.dto.MangaDto;
import com.collectorhub.collectorhub.dto.UserDto;
import com.collectorhub.collectorhub.dto.mappers.AbstractMangaDtoMapper;
import com.collectorhub.collectorhub.dto.mappers.AbstractUserDtoMapper;
import com.collectorhub.collectorhub.services.JwtService;
import com.collectorhub.collectorhub.services.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    //TODO: AJUSTAR NOMBRE DE LOS ENDPOINTS, NO QUE TODOS SE LLAMEN /user

    @Autowired
    private UserService userService;

    @Autowired
    private AbstractUserDtoMapper userDtoMapper;

    @Autowired
    private AbstractMangaDtoMapper mangaDtoMapper;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/add-manga/{mangaId}")
    public ResponseEntity<String> addMangaToUser(@PathVariable Long mangaId, @AuthenticationPrincipal UserEntity user) {
        if (user == null) {
            return ResponseEntity.status(401).body("User not authenticated");
        }

        return userService.addMangaToUser(user, mangaId);
    }

    @GetMapping("/mangas")
    public ResponseEntity<MangaResponseList> getUserMangas(@AuthenticationPrincipal UserEntity user) {
        Long userId = user.getId();
        List<MangaDto> userMangas = userService.getUserMangas(userId);

        if (userMangas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        MangaResponseList mangaResponseList = new MangaResponseList(mangaDtoMapper.fromMangaDtoListToMangaResponseList(userMangas));
        return ResponseEntity.ok(mangaResponseList);
    }

    @PostMapping("/add-desired-manga/{mangaId}")
    public ResponseEntity<String> addDesiredMangaToUser(@PathVariable Long mangaId, @AuthenticationPrincipal UserEntity user) {
        if (user == null) {
            return ResponseEntity.status(401).body("User not authenticated");
        }

        return userService.addDesiredMangaToUser(user, mangaId);
    }

    @GetMapping("/desired-mangas")
    public ResponseEntity<MangaResponseList> getUserDesiredMangas(@AuthenticationPrincipal UserEntity user) {
        Long userId = user.getId();
        List<MangaDto> userMangas = userService.getUserDesiredMangas(userId);

        if (userMangas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        MangaResponseList mangaResponseList = new MangaResponseList(mangaDtoMapper.fromMangaDtoListToMangaResponseList(userMangas));
        return ResponseEntity.ok(mangaResponseList);
    }

    @PostMapping("/new")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse createdUser = userDtoMapper.fromUserDtoToUserResponse(userService.createUser(userDtoMapper.fromUserRequestToUserDto(userRequest)));
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }


    @GetMapping("/getOne/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse user = userDtoMapper.fromUserDtoToUserResponse(userService.findById(id));
        return ResponseEntity.ok(user);
    }

    @GetMapping("/get-authenticated-user")
    public ResponseEntity<UserResponse> getAuthenticatedUser(@AuthenticationPrincipal UserEntity user) {
        Long userId = user.getId();

        UserResponse userResponse = userDtoMapper.fromUserDtoToUserResponse(userService.findById(userId));
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/getUserProfile")
    public ResponseEntity<UserResponse> getUserById(@AuthenticationPrincipal UserEntity user) {
        Long userId = user.getId();

        UserResponse userResponse = userDtoMapper.fromUserDtoToUserResponse(userService.findById(userId));
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/{userId}/username")
    public ResponseEntity<String> getUsername(@PathVariable Long userId) {
        UserDto userOpt = userService.findById(userId);

        if (userOpt != null) {
            return ResponseEntity.ok(userOpt.getUsername());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Desconocido");
        }
    }


    @GetMapping("/all")
    public ResponseEntity<UserResponseList> getAllUsers() {
        List<UserResponse> allUsers = userDtoMapper.fromUserDtoListToUserResponseList(userService.getAllUsers());
        UserResponseList responseList = new UserResponseList(allUsers);
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/countAll")
    public ResponseEntity<Long> getCountUsers() {
        long userCount = userService.countAllUsers();
        return ResponseEntity.ok(userCount);
    }

    @GetMapping("/countUsersSubscribed")
    public ResponseEntity<Long> getCountUsersSubscribed() {
        long userCount = userService.countUsersSubscribed();
        return ResponseEntity.ok(userCount);
    }


    @PutMapping("/upd/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        UserResponse updatedUser = userDtoMapper.fromUserDtoToUserResponse(userService.updateUser(userDtoMapper.fromUserRequestToUserDto(userRequest), id));
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/upd-auth-user")
    public ResponseEntity<UpdateUserResponse> updateUser(@AuthenticationPrincipal UserEntity user, @RequestBody UserRequest userRequest) {
        Long userId = user.getId();
        UserDto updatedUser = (userService.updateUser(userDtoMapper.fromUserRequestToUserDto(userRequest), userId));

        String newToken = jwtService.getToken(userDtoMapper.fromUserDtoToUserEntity(updatedUser));

        UpdateUserResponse response = new UpdateUserResponse();
        response.setUser(updatedUser);
        response.setToken(newToken);

        return ResponseEntity.ok(response);
    }


    @PostMapping("/getUsersFilter")
    public ResponseEntity<List<UserResponse>> getUsersFilter(@RequestBody UserFilterRequest filter) {
        List<UserDto> users = userService.filterUsers(filter);

        List<UserResponse> userResponses = users.stream()
                .map(userDto -> userDtoMapper.fromUserDtoToUserResponse(userDto))
                .collect(Collectors.toList());

        return ResponseEntity.ok(userResponses);
    }


    /*

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteRate(id);
        return ResponseEntity.noContent().build();
    }
*/
}
