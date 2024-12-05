package com.collectorhub.collectorhub.database.repositories;

import com.collectorhub.collectorhub.database.entities.MangaEntity;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MangaRepository extends JpaRepository<MangaEntity, UUID> {

    MangaEntity findByTitle(String title);

    List<MangaEntity> findByAuthor(String author);

    List<MangaEntity> findByGenreName(String genreName);

    List<MangaEntity> findByChapters(int chapters);

    List<MangaEntity> findByChaptersLessThanEqual(int maxChapters);
    MangaEntity findById(Long UUID);

    boolean existsByTitle(String tittle);

    List<MangaEntity> findAllByIdIn(List<Long> uuidList);

    List<MangaEntity> findByCompletedTrue();

    List<MangaEntity> findByPropietarios_Id(Long userId);

    List<MangaEntity> findByAuthorIn(List<String> topAuthors);

    Optional<MangaEntity> findByTitleIgnoreCase(String title);

    List<MangaEntity> findByReleaseDateAfter(LocalDate thirtyDaysAgo);
}