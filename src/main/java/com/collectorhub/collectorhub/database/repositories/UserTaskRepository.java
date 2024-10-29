package com.collectorhub.collectorhub.database.repositories;

import com.collectorhub.collectorhub.database.entities.UserEntity;
import com.collectorhub.collectorhub.database.entities.UserTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserTaskRepository extends JpaRepository<UserTaskEntity, UUID> {

    List<UserTaskEntity> findByUserAndIsCompleted(UserEntity user, boolean isCompleted);

}
