package com.collectorhub.collectorhub.controller;

import com.collectorhub.collectorhub.controller.request.RateRequest;
import com.collectorhub.collectorhub.controller.request.UserRequest;
import com.collectorhub.collectorhub.controller.response.RateResponse;
import com.collectorhub.collectorhub.controller.response.UserResponse;
import com.collectorhub.collectorhub.controller.response.UserResponseList;
import com.collectorhub.collectorhub.dto.mappers.AbstractRateDtoMapper;
import com.collectorhub.collectorhub.dto.mappers.AbstractUserDtoMapper;
import com.collectorhub.collectorhub.services.RateService;
import com.collectorhub.collectorhub.services.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    /*
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID id) {
        UserResponse user = userDtoMapper.fromRateDtoToRateResponse(userService.getRateById(id));
        return ResponseEntity.ok(user);
    }*/

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

/*
    @PutMapping("/upd/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID id, @RequestBody RateRequest rateRequest) {
        UserResponse updatedUser = userDtoMapper.fromRateDtoToRateResponse(userService.updateRate(userDtoMapper.fromRateRequestToRateDto(rateRequest), id));
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteRate(id);
        return ResponseEntity.noContent().build();
    }
*/
}
