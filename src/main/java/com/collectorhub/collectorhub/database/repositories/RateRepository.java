package com.collectorhub.collectorhub.database.repositories;

import com.collectorhub.collectorhub.controller.response.RateResponse;
import com.collectorhub.collectorhub.database.entities.MangaEntity;
import com.collectorhub.collectorhub.database.entities.RateEntity;
import com.collectorhub.collectorhub.database.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RateRepository extends JpaRepository<RateEntity, UUID> {

    List<RateEntity> findByUser(UserEntity user);

    List<RateEntity> findByMangaId(Long id);

    @Query("SELECT COUNT(r) > 0 FROM RateEntity r WHERE r.user = :user AND r.manga = :manga")
    boolean existsByUserAndManga(@Param("user") UserEntity user, @Param("manga") MangaEntity manga);
}
