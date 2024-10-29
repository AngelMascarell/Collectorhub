package com.collectorhub.collectorhub.dto.mappers;

import com.collectorhub.collectorhub.controller.request.TaskRequest;
import com.collectorhub.collectorhub.controller.response.TaskResponse;
import com.collectorhub.collectorhub.database.entities.TaskEntity;
import com.collectorhub.collectorhub.dto.TaskDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-29T14:26:56+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class AbstractTaskDtoMapperImpl implements AbstractTaskDtoMapper {

    @Override
    public TaskDto fromTaskEntityToTaskDto(TaskEntity task) {
        if ( task == null ) {
            return null;
        }

        TaskDto.TaskDtoBuilder taskDto = TaskDto.builder();

        taskDto.id( task.getId() );
        taskDto.description( task.getDescription() );
        taskDto.title( task.getTitle() );
        taskDto.taskType( task.getTaskType() );

        return taskDto.build();
    }

    @Override
    public List<TaskDto> fromTaskEntityListToTaskDtoList(List<TaskEntity> taskList) {
        if ( taskList == null ) {
            return null;
        }

        List<TaskDto> list = new ArrayList<TaskDto>( taskList.size() );
        for ( TaskEntity taskEntity : taskList ) {
            list.add( fromTaskEntityToTaskDto( taskEntity ) );
        }

        return list;
    }

    @Override
    public TaskEntity fromTaskDtoToTaskEntity(TaskDto taskDto) {
        if ( taskDto == null ) {
            return null;
        }

        TaskEntity.TaskEntityBuilder<?, ?> taskEntity = TaskEntity.builder();

        taskEntity.id( taskDto.getId() );
        taskEntity.title( taskDto.getTitle() );
        taskEntity.description( taskDto.getDescription() );
        taskEntity.taskType( taskDto.getTaskType() );

        return taskEntity.build();
    }

    @Override
    public TaskDto fromTaskRequestToTaskDto(TaskRequest request) {
        if ( request == null ) {
            return null;
        }

        TaskDto.TaskDtoBuilder taskDto = TaskDto.builder();

        taskDto.description( request.getDescription() );
        taskDto.title( request.getTitle() );
        taskDto.taskType( request.getTaskType() );

        return taskDto.build();
    }

    @Override
    public TaskResponse fromTaskDtoToTaskResponse(TaskDto taskDto) {
        if ( taskDto == null ) {
            return null;
        }

        TaskResponse.TaskResponseBuilder taskResponse = TaskResponse.builder();

        taskResponse.id( taskDto.getId() );
        taskResponse.description( taskDto.getDescription() );
        taskResponse.title( taskDto.getTitle() );
        taskResponse.taskType( taskDto.getTaskType() );

        return taskResponse.build();
    }
}
