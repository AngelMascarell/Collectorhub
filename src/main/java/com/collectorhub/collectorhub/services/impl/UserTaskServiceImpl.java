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
import com.collectorhub.collectorhub.services.UserTaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserTaskServiceImpl implements UserTaskService {

    @Autowired
    private AbstractTaskDtoMapper taskDtoMapper;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTaskRepository userTaskRepository;

    @Override
    public void checkTasksCompletion(UserEntity user) {
        List<UserTaskEntity> userTasks = user.getUserTasks();

        for (UserTaskEntity userTask : userTasks) {
            TaskEntity task = userTask.getTask();
            String description = task.getDescription();

            // Aquí verificamos si se cumple la condición
            if (description.equals("Tener más de 5 mangas en tu colección personal")) {
                if (user.getMangas().size() > 5) {
                    userTask.setCompleted(true); // Marca la tarea como completada
                }
            }

            // Aquí puedes agregar más condiciones para otras tareas
        }
    }

    @Override
    @Transactional
    public void updateTaskCompletion(UserEntity user) {
        checkTasksCompletion(user);
        // Guarda los cambios en la base de datos
        userTaskRepository.saveAll(user.getUserTasks());
    }

    @Override
    public int getCompletedTasksCount(Long userId) {
        UserEntity userEntity = userRepository.findById(userId);

        if (userEntity != null) {
            List<UserTaskEntity> userTasks = userEntity.getUserTasks();

            // Contar las tareas completadas
            int completedCount = 0;
            for (UserTaskEntity userTask : userTasks) {
                if (userTask.isCompleted()) {
                    completedCount++;
                }
            }

            return completedCount;
        }
        return 0;
    }
}
