package com.collectorhub.collectorhub.dto.mappers;

import com.collectorhub.collectorhub.controller.request.TaskRequest;
import com.collectorhub.collectorhub.controller.response.TaskResponse;
import com.collectorhub.collectorhub.database.entities.TaskEntity;
import com.collectorhub.collectorhub.dto.TaskDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AbstractTaskDtoMapper {

    TaskDto fromTaskEntityToTaskDto(TaskEntity task);

    List<TaskDto> fromTaskEntityListToTaskDtoList(List<TaskEntity> taskList);

    TaskEntity fromTaskDtoToTaskEntity(TaskDto taskDto);

    TaskDto fromTaskRequestToTaskDto(TaskRequest request);
    TaskResponse fromTaskDtoToTaskResponse(TaskDto taskDto);


}
