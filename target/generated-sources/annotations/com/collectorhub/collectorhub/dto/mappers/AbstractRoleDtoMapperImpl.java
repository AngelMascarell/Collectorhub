package com.collectorhub.collectorhub.dto.mappers;

import com.collectorhub.collectorhub.database.entities.RoleEntity;
import com.collectorhub.collectorhub.dto.RoleDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-12T13:54:54+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class AbstractRoleDtoMapperImpl implements AbstractRoleDtoMapper {

    @Override
    public RoleDto fromRoleEntityToRoleDto(RoleEntity role) {
        if ( role == null ) {
            return null;
        }

        RoleDto.RoleDtoBuilder roleDto = RoleDto.builder();

        roleDto.id( role.getId() );
        roleDto.name( role.getName() );

        return roleDto.build();
    }
}
