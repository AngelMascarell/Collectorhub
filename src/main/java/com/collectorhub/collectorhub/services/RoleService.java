package com.collectorhub.collectorhub.services;

import com.collectorhub.collectorhub.database.entities.RoleEntity;
import com.collectorhub.collectorhub.dto.RoleDto;

import java.util.List;

public interface RoleService {

    public List<RoleDto> getAllRoles();

    public RoleDto getRoleById(Long id);

    public RoleDto createRole(RoleDto role);
}
