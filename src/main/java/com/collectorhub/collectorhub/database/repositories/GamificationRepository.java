package com.collectorhub.collectorhub.database.repositories;

import com.collectorhub.collectorhub.database.entities.GamificationEntity;
import com.collectorhub.collectorhub.database.entities.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GamificationRepository extends JpaRepository<GamificationEntity, UUID> {

    @Query("SELECT g FROM GamificationEntity g WHERE g.id NOT IN " +
            "(SELECT ug.gamification.id FROM UserGamificationEntity ug WHERE ug.user.id = :userId)")
    List<GamificationEntity> findGamificationsNotAwardedToUser(@Param("userId") Long userId);

}
