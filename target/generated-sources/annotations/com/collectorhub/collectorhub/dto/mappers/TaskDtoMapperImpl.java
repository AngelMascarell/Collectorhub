package com.collectorhub.collectorhub.dto.mappers;

import com.collectorhub.collectorhub.database.entities.TaskEntity;
import com.collectorhub.collectorhub.dto.TaskDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-28T13:06:23+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class TaskDtoMapperImpl implements TaskDtoMapper {

    @Override
    public TaskDto fromTaskEntityToTaskDto(TaskEntity task) {
        if ( task == null ) {
            return null;
        }

        TaskDto.TaskDtoBuilder taskDto = TaskDto.builder();

        taskDto.id( task.getId() );
        taskDto.description( task.getDescription() );

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
        taskEntity.description( taskDto.getDescription() );

        return taskEntity.build();
    }
}
