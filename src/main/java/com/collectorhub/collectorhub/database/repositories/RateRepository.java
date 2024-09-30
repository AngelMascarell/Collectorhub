package com.collectorhub.collectorhub.database.repositories;

import com.collectorhub.collectorhub.database.entities.RateEntity;
import com.collectorhub.collectorhub.database.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RateRepository extends JpaRepository<RateEntity, UUID> {

    List<RateEntity> findByUser(UserEntity user);

    List<RateEntity> findByMangaId(Long id);

}
