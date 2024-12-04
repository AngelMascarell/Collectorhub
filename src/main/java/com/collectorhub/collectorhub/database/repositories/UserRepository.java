package com.collectorhub.collectorhub.database.repositories;

import com.collectorhub.collectorhub.database.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    UserEntity findByUsername(String username);

    UserEntity findByEmail(String email);

    UserEntity findById(Long UUID);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
