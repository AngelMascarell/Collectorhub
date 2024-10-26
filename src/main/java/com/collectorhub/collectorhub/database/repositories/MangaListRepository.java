package com.collectorhub.collectorhub.database.repositories;


import com.collectorhub.collectorhub.database.entities.MangaEntity;
import com.collectorhub.collectorhub.database.entities.MangaListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MangaListRepository extends JpaRepository<MangaListEntity, UUID> {



}
