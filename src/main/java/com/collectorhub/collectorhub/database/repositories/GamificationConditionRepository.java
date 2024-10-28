package com.collectorhub.collectorhub.database.repositories;

import com.collectorhub.collectorhub.database.entities.GamificationConditionEntity;
import com.collectorhub.collectorhub.database.entities.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GamificationConditionRepository extends JpaRepository<GamificationConditionEntity, UUID> {



}
