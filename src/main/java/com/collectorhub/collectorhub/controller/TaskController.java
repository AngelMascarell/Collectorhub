package com.collectorhub.collectorhub.controller;

import com.collectorhub.collectorhub.controller.request.TaskRequest;
import com.collectorhub.collectorhub.controller.response.TaskResponse;
import com.collectorhub.collectorhub.database.entities.UserEntity;
import com.collectorhub.collectorhub.database.repositories.UserRepository;
import com.collectorhub.collectorhub.dto.TaskDto;
import com.collectorhub.collectorhub.dto.mappers.AbstractTaskDtoMapper;
import com.collectorhub.collectorhub.services.GamificationService;
import com.collectorhub.collectorhub.services.TaskService;
import com.collectorhub.collectorhub.services.UserTaskService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private AbstractTaskDtoMapper taskDtoMapper;

    @Autowired
    private UserTaskService userTaskService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GamificationService gamificationService;

    @PostMapping("/checkCompletion")
    public ResponseEntity<String> checkTaskCompletion(@AuthenticationPrincipal UserEntity user) {
        Long userId = user.getId();

        Optional<UserEntity> optionalUser = Optional.ofNullable(userRepository.findById(userId));

        if (optionalUser.isPresent()) {
            UserEntity userFind = optionalUser.get();
            userTaskService.updateTaskCompletion(userFind);

            String awardedGamification = gamificationService.checkGamificationsForUser(userFind);

            if (awardedGamification != null) {
                return ResponseEntity.ok("Gamificación conseguida: " + awardedGamification);
            } else {
                return ResponseEntity.ok("No se ha conseguido ninguna nueva gamificación.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
        }
    }


    @PostMapping("/new")
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest request) {
        TaskDto taskDto = taskService.createTask(taskDtoMapper.fromTaskRequestToTaskDto(request));
        return new ResponseEntity<>(taskDtoMapper.fromTaskDtoToTaskResponse(taskDto), HttpStatus.CREATED);
    }
}
