package com.collectorhub.collectorhub.services.impl;

import com.collectorhub.collectorhub.controller.request.GamificationRequest;
import com.collectorhub.collectorhub.controller.response.GamificationResponse;
import com.collectorhub.collectorhub.database.entities.GamificationEntity;
import com.collectorhub.collectorhub.database.entities.TaskEntity;
import com.collectorhub.collectorhub.database.repositories.GamificationConditionRepository;
import com.collectorhub.collectorhub.database.repositories.GamificationRepository;
import com.collectorhub.collectorhub.database.repositories.TaskRepository;
import com.collectorhub.collectorhub.dto.GamificationConditionDto;
import com.collectorhub.collectorhub.dto.GamificationDto;
import com.collectorhub.collectorhub.dto.mappers.AbstractGamificationConditionDtoMapper;
import com.collectorhub.collectorhub.dto.mappers.AbstractGamificationDtoMapper;
import com.collectorhub.collectorhub.services.GamificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class GamificationServiceImpl implements GamificationService {


    @Autowired
    private GamificationRepository gamificationRepository;

    @Autowired
    private GamificationConditionRepository gamificationConditionRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AbstractGamificationDtoMapper gamificationDtoMapper;

    @Autowired
    private AbstractGamificationConditionDtoMapper gamificationConditionDtoMapper;


    // Método para crear una nueva gamificación
    public GamificationDto createGamification(GamificationRequest gamification) {
        gamificationRepository.save(gamificationDtoMapper.fromGamificationDtoToGamificationEntity(gamificationDtoMapper.fromGamificationRequestToGamificationDto(gamification)));
        return gamificationDtoMapper.fromGamificationRequestToGamificationDto(gamification);
    }

    // Método para obtener una gamificación por ID
    public Optional<GamificationDto> getGamificationById(UUID id) {
        Optional<GamificationEntity> gamificationEntity = gamificationRepository.findById(id);

        if (gamificationEntity.isPresent()) {
            return Optional.of(gamificationDtoMapper.fromGamificationEntityToGamificationDto(gamificationEntity.get()));
        } else {
            return Optional.empty();
        }
    }

    // Método para obtener todas las gamificaciones
    public List<GamificationDto> getAllGamification() {
        return gamificationDtoMapper.fromGamificationEntityListToGamificationDtoList(gamificationRepository.findAll());
    }

    // Método para agregar condiciones a una gamificación
    public GamificationDto addConditionToGamification(UUID gamificationId, GamificationConditionDto condition) {
        GamificationEntity gamification = gamificationRepository.findById(gamificationId).orElseThrow();
        gamification.getConditions().add(gamificationConditionDtoMapper.fromGamificationConditionDtoToGamificationConditionEntity(condition));
        gamificationRepository.save(gamification);
        return gamificationDtoMapper.fromGamificationEntityToGamificationDto(gamification);
    }

    // Método para verificar si un usuario puede ganar una gamificación
    public boolean checkIfUserCanEarnGamification(Long userId, UUID gamificationId) {
        List<GamificationConditionDto> conditions = getConditionsForGamification(gamificationId);

        for (GamificationConditionDto condition : conditions) {
            if (!isConditionMet(userId, condition)) {
                return false; // Si alguna condición no se cumple, retorna falso
            }
        }
        return true; // Todas las condiciones se cumplen
    }

    // Método privado para verificar si se cumple una condición específica
    private boolean isConditionMet(Long userId, GamificationConditionDto condition) {
        switch (condition.getType()) {
            case "TASK_COMPLETED":
                int completedTasks = getCompletedTasksCount(userId);
                return completedTasks >= condition.getThreshold();
            // Agregar más condiciones aquí si es necesario
            default:
                return false; // Condición no reconocida
        }
    }

    // Método para obtener las condiciones de una gamificación específica
    private List<GamificationConditionDto> getConditionsForGamification(UUID gamificationId) {
        GamificationEntity gamificationEntity = gamificationRepository.findById(gamificationId)
                .orElseThrow(() -> new RuntimeException("Gamification not found"));

        return gamificationConditionDtoMapper.fromGamificationConditionEntityListToGamificationConditionDtoList(gamificationEntity.getConditions());
    }


    // Método para contar las tareas completadas por un usuario (simulado)
    private int getCompletedTasksCount(Long userId) {
        // Contar las tareas completadas por el usuario
        List<TaskEntity> completedTasks = taskRepository.findByUserIdAndIsCompleted(userId, true);
        return completedTasks.size(); // Devuelve el número de tareas completadas
    }

    // Método para eliminar una gamificación por ID
    public void deleteGamification(UUID id) {
        gamificationRepository.deleteById(id);
    }
}
