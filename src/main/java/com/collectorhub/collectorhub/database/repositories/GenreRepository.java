package com.collectorhub.collectorhub.database.repositories;

import com.collectorhub.collectorhub.database.entities.GenreEntity;
import com.collectorhub.collectorhub.database.entities.MangaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, UUID> {

    GenreEntity findByName(String name);

    GenreEntity findById(Long id);

}
