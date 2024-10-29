package com.collectorhub.collectorhub.services.impl;

import com.collectorhub.collectorhub.database.entities.TaskEntity;
import com.collectorhub.collectorhub.database.entities.UserEntity;
import com.collectorhub.collectorhub.database.entities.UserTaskEntity;
import com.collectorhub.collectorhub.database.repositories.TaskRepository;
import com.collectorhub.collectorhub.database.repositories.UserRepository;
import com.collectorhub.collectorhub.database.repositories.UserTaskRepository;
import com.collectorhub.collectorhub.dto.TaskDto;
import com.collectorhub.collectorhub.dto.mappers.AbstractTaskDtoMapper;
import com.collectorhub.collectorhub.services.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.oauth2.resourceserver.OAuth2ResourceServerSecurityMarker;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    @Autowired
    private AbstractTaskDtoMapper taskDtoMapper;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTaskRepository userTaskRepository;

    public TaskDto createTask(TaskDto taskDto) {
        TaskEntity task = taskDtoMapper.fromTaskDtoToTaskEntity(taskDto);
        task = taskRepository.save(task);

        List<UserEntity> allUsers = userRepository.findAll();
        for (UserEntity user : allUsers) {
            UserTaskEntity userTask = new UserTaskEntity();
            userTask.setUser(user);
            userTask.setTask(task);
            userTaskRepository.save(userTask);
        }
        return taskDtoMapper.fromTaskEntityToTaskDto(task);
    }
}
