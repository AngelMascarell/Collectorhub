package com.collectorhub.collectorhub.database.repositories;

import com.collectorhub.collectorhub.database.entities.MangaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MangaRepository extends JpaRepository<MangaEntity, UUID> {

    MangaEntity findByTitle(String title);

    List<MangaEntity> findByAuthor(String author);

    List<MangaEntity> findByGenreName(String genreName);

    List<MangaEntity> findByChapters(int chapters);

    List<MangaEntity> findByCompletedTrue();

    MangaEntity findById(Long UUID);

    boolean existsByTitle(String tittle);

}
