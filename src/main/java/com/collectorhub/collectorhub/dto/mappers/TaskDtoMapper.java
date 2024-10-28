package com.collectorhub.collectorhub.dto.mappers;

import com.collectorhub.collectorhub.controller.request.GamificationRequest;
import com.collectorhub.collectorhub.controller.response.GamificationResponse;
import com.collectorhub.collectorhub.database.entities.GamificationEntity;
import com.collectorhub.collectorhub.database.entities.TaskEntity;
import com.collectorhub.collectorhub.dto.GamificationDto;
import com.collectorhub.collectorhub.dto.TaskDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskDtoMapper {

    TaskDto fromTaskEntityToTaskDto(TaskEntity task);

    List<TaskDto> fromTaskEntityListToTaskDtoList(List<TaskEntity> taskList);

    TaskEntity fromTaskDtoToTaskEntity(TaskDto taskDto);


}
