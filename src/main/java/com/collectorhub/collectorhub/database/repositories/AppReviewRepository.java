package com.collectorhub.collectorhub.database.repositories;

import com.collectorhub.collectorhub.database.entities.AppReviewEntity;
import com.collectorhub.collectorhub.database.entities.RateEntity;
import com.collectorhub.collectorhub.database.entities.UserEntity;
import com.collectorhub.collectorhub.dto.AppReviewDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AppReviewRepository extends JpaRepository<AppReviewEntity, UUID> {

    List<AppReviewEntity> findByUserId(Long userId);

}
