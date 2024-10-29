package com.collectorhub.collectorhub.services.impl;

import com.collectorhub.collectorhub.controller.request.GamificationRequest;
import com.collectorhub.collectorhub.controller.response.GamificationResponse;
import com.collectorhub.collectorhub.database.entities.*;
import com.collectorhub.collectorhub.database.repositories.*;
import com.collectorhub.collectorhub.dto.GamificationConditionDto;
import com.collectorhub.collectorhub.dto.GamificationDto;
import com.collectorhub.collectorhub.dto.mappers.AbstractGamificationConditionDtoMapper;
import com.collectorhub.collectorhub.dto.mappers.AbstractGamificationDtoMapper;
import com.collectorhub.collectorhub.services.GamificationService;
import com.collectorhub.collectorhub.services.UserTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    private UserTaskRepository userTaskRepository;

    @Autowired
    private UserTaskService userTaskService;

    @Autowired
    private UserGamificationRepository userGamificationRepository;

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
    public boolean checkIfUserCanEarnGamification(UserEntity user, UUID gamificationId) {
        List<GamificationConditionDto> conditions = getConditionsForGamification(gamificationId);

        for (GamificationConditionDto condition : conditions) {
            if (!isConditionMet(user, condition)) {
                return false; // Si alguna condición no se cumple, retorna falso
            }
        }
        return true; // Todas las condiciones se cumplen
    }

    // Método privado para verificar si se cumple una condición específica
    private boolean isConditionMet(UserEntity user, GamificationConditionDto condition) {
        switch (condition.getType()) {
            case "TASK_COMPLETED":
                int completedTasks = getCompletedTasksCount(user);
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
    private int getCompletedTasksCount(UserEntity user) {
        // Contar las tareas completadas por el usuario
        List<UserTaskEntity> completedUserTasks  = userTaskRepository.findByUserAndIsCompleted(user, true);
        return completedUserTasks .size(); // Devuelve el número de tareas completadas
    }

    // Método para eliminar una gamificación por ID
    public void deleteGamification(UUID id) {
        gamificationRepository.deleteById(id);
    }

    @Override
    public void checkGamificationsForUser(UserEntity user) {
        List<GamificationEntity> gamifications = gamificationRepository.findAll();

        for (GamificationEntity gamification : gamifications) {
            boolean conditionsMet = checkConditions(user, gamification.getConditions());
            if (conditionsMet) {
                // Lógica para premiar al usuario
                awardGamificationToUser(user, gamification);
            }
        }
    }

    private boolean checkConditions(UserEntity user, List<GamificationConditionEntity> conditions) {
        for (GamificationConditionEntity condition : conditions) {
            // Lógica para comprobar la condición según su tipo y umbral
            if (condition.getType().equals("TASK_COMPLETED")) {
                int completedTasksCount = userTaskService.getCompletedTasksCount(user.getId());
                if (completedTasksCount < condition.getThreshold()) {
                    return false; // No se cumple esta condición
                }
            }
            // Puedes agregar más condiciones aquí
        }
        return true; // Todas las condiciones se cumplen
    }

    private void awardGamificationToUser(UserEntity user, GamificationEntity gamification) {
        UserGamificationEntity userGamification = UserGamificationEntity.builder()
                .user(user)
                .gamification(gamification)
                .awardedAt(LocalDateTime.now())
                .build();

        userGamificationRepository.save(userGamification);
    }

}
