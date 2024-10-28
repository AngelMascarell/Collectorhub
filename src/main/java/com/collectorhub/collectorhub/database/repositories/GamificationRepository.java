package com.collectorhub.collectorhub.database.repositories;

import com.collectorhub.collectorhub.database.entities.GamificationEntity;
import com.collectorhub.collectorhub.database.entities.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GamificationRepository extends JpaRepository<GamificationEntity, UUID> {



}
