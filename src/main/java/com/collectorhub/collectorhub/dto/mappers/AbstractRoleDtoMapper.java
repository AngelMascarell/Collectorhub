package com.collectorhub.collectorhub.dto.mappers;

import com.collectorhub.collectorhub.database.entities.RoleEntity;
import com.collectorhub.collectorhub.database.entities.TaskEntity;
import com.collectorhub.collectorhub.dto.RoleDto;
import com.collectorhub.collectorhub.dto.TaskDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AbstractRoleDtoMapper {


    RoleDto fromRoleEntityToRoleDto(RoleEntity role);

}
