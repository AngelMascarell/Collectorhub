package com.collectorhub.collectorhub.controller;

import com.collectorhub.collectorhub.controller.request.UserFilterRequest;
import com.collectorhub.collectorhub.controller.request.UserRequest;
import com.collectorhub.collectorhub.controller.response.UserResponse;
import com.collectorhub.collectorhub.controller.response.UserResponseList;
import com.collectorhub.collectorhub.dto.UserDto;
import com.collectorhub.collectorhub.dto.mappers.AbstractUserDtoMapper;
import com.collectorhub.collectorhub.services.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PostMapping("/new")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse createdUser = userDtoMapper.fromUserDtoToUserResponse(userService.createUser(userDtoMapper.fromUserRequestToUserDto(userRequest)));
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }


    @GetMapping("getOne/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse user = userDtoMapper.fromUserDtoToUserResponse(userService.findById(id));
        return ResponseEntity.ok(user);
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
