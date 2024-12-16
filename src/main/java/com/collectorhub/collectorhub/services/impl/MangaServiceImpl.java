package com.collectorhub.collectorhub.services.impl;

import com.collectorhub.collectorhub.database.entities.GenreEntity;
import com.collectorhub.collectorhub.database.entities.MangaEntity;
import com.collectorhub.collectorhub.database.entities.UserEntity;
import com.collectorhub.collectorhub.database.repositories.GenreRepository;
import com.collectorhub.collectorhub.database.repositories.MangaRepository;
import com.collectorhub.collectorhub.database.repositories.UserRepository;
import com.collectorhub.collectorhub.dto.MangaDto;
import com.collectorhub.collectorhub.dto.mappers.AbstractMangaDtoMapper;
import com.collectorhub.collectorhub.services.MangaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MangaServiceImpl implements MangaService {

    @Autowired
    private MangaRepository mangaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AbstractMangaDtoMapper mangaDtoMapper;

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public MangaDto findByTitle(String title) {
        return mangaDtoMapper.fromMangaEntityToMangaDto(mangaRepository.findByTitle(title));
    }

    @Override
    public List<MangaDto> findByAuthor(String author) {
        return mangaDtoMapper.fromMangaEntityListToMangaDtoList(mangaRepository.findByAuthor(author));
    }

    @Override
    public List<MangaDto> findByGenreName(String genreName) {
        return mangaDtoMapper.fromMangaEntityListToMangaDtoList(mangaRepository.findByGenreName(genreName));
    }

    @Override
    public List<MangaDto> findByChapters(int chapters) {
        return mangaDtoMapper.fromMangaEntityListToMangaDtoList(mangaRepository.findByChapters(chapters));
    }

    @Override
    public List<MangaDto> findByCompletedTrue() {
        return mangaDtoMapper.fromMangaEntityListToMangaDtoList(mangaRepository.findByCompletedTrue());
    }

    @Override
    public MangaDto createManga(MangaDto mangaDto) {
        if (mangaRepository.existsByTitle(mangaDto.getTitle())) {
            throw new IllegalArgumentException("A manga with this title already exists: " + mangaDto.getTitle());
        }

        MangaEntity mangaEntity = MangaEntity.builder()
                .author(mangaDto.getAuthor())
                .completed(mangaDto.isCompleted())
                .chapters(mangaDto.getChapters())
                .title(mangaDto.getTitle())
                .imageUrl(mangaDto.getImageUrl())
                .releaseDate(mangaDto.getReleaseDate())
                .synopsis(mangaDto.getSynopsis())
                .build();

        if (mangaDto.getGenreId() != null) {
            GenreEntity genre = genreRepository.findById(mangaDto.getGenreId());
            if (genre == null) {
                throw new EntityNotFoundException("Genre not found with id: " + mangaDto.getGenreId());
            }
            mangaEntity.setGenre(genre);
        }

        MangaEntity savedMangaEntity = mangaRepository.save(mangaEntity);

        return mangaDtoMapper.fromMangaEntityToMangaDto(savedMangaEntity);
    }


    @Override
    public MangaDto getMangaById(Long id) {
        Optional<MangaEntity> mangaOptional = Optional.ofNullable(mangaRepository.findById(id));
        if (mangaOptional.isPresent()) {
            MangaEntity mangaEntity = mangaOptional.get();
            return mapEntityToDto(mangaEntity);
        } else {
            throw new EntityNotFoundException("Manga not found with id: " + id);
        }
    }

    private MangaDto mapEntityToDto(MangaEntity mangaEntity) {
        return MangaDto.builder()
                .id(mangaEntity.getId())
                .title(mangaEntity.getTitle())
                .author(mangaEntity.getAuthor())
                .genreId(mangaEntity.getGenre() != null ? mangaEntity.getGenre().getId() : null)
                .chapters(mangaEntity.getChapters())
                .completed(mangaEntity.isCompleted())
                .imageUrl(mangaEntity.getImageUrl())
                .synopsis(mangaEntity.getSynopsis())
                .releaseDate(mangaEntity.getReleaseDate())
                //.propietarios(mangaEntity.getPropietarios())
                .build();
    }

    @Override
    public List<MangaDto> getAllMangas() {
        List<MangaEntity> mangas = mangaRepository.findAll();
        List<MangaEntity> filteredMangas = mangas.stream()
                .filter(manga -> !manga.getImageUrl().startsWith("https:"))
                .collect(Collectors.toList());

        return mangaDtoMapper.fromMangaEntityListToMangaDtoList(filteredMangas);
    }


    @Override
    public MangaDto updateManga(MangaDto mangaDto, UUID id) {
        MangaEntity existingManga = mangaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Manga not found with id: " + id));

        existingManga.setTitle(mangaDto.getTitle());
        existingManga.setAuthor(mangaDto.getAuthor());
        existingManga.setChapters(mangaDto.getChapters());
        existingManga.setCompleted(mangaDto.isCompleted());

        MangaEntity updatedManga = mangaRepository.save(existingManga);

        return mangaDtoMapper.fromMangaEntityToMangaDto(updatedManga);
    }


    @Override
    public void deleteManga(UUID id) {
        mangaRepository.deleteById(id);
    }

    @Override
    public boolean existsByTitle(String title) {
        return mangaRepository.existsByTitle(title);
    }

    @Override
    public long countAllMangas() {
        return mangaRepository.count();
    }

    @Override
    public List<MangaDto> findMangasByIds(List<Long> mangaIds) {
        List<MangaEntity> mangaEntities = mangaRepository.findAllByIdIn(mangaIds);

        return mangaEntities.stream()
                .map(mangaDtoMapper::fromMangaEntityToMangaDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MangaDto> getCompletedMangas() {
        List<MangaEntity> completedMangas = mangaRepository.findByCompletedTrue();
        List<MangaEntity> filteredMangas = completedMangas.stream()
                .filter(manga -> !manga.getImageUrl().startsWith("https:"))
                .collect(Collectors.toList());

        return filteredMangas.stream()
                .map(mangaDtoMapper::fromMangaEntityToMangaDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<MangaDto> getUnder100Mangas() {
        List<MangaEntity> under100Mangas = mangaRepository.findByChaptersLessThanEqual(100);
        List<MangaEntity> filteredMangas = under100Mangas.stream()
                .filter(manga -> !manga.getImageUrl().startsWith("https:"))
                .collect(Collectors.toList());

        return filteredMangas.stream()
                .map(mangaDtoMapper::fromMangaEntityToMangaDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<MangaDto> getPersonalizedMangas(Long userId) {
        UserEntity user = userRepository.findById(userId);

        List<MangaEntity> userCollection = user.getMangas();

        Set<Long> userCollectionIds = userCollection.stream()
                .map(MangaEntity::getId)
                .collect(Collectors.toSet());

        Map<String, Long> authorCount = userCollection.stream()
                .collect(Collectors.groupingBy(MangaEntity::getAuthor, Collectors.counting()));

        List<String> topAuthors = authorCount.entrySet().stream()
                .sorted((entry1, entry2) -> Long.compare(entry2.getValue(), entry1.getValue()))
                .limit(2)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        List<MangaEntity> filteredMangas = mangaRepository.findAll().stream()
                .filter(manga -> topAuthors.contains(manga.getAuthor()))
                .filter(manga -> !userCollectionIds.contains(manga.getId()))
                .filter(manga -> !manga.getImageUrl().startsWith("https:"))
                .collect(Collectors.toList());

        if (filteredMangas.isEmpty()) {
            filteredMangas = mangaRepository.findAll().stream()
                    .filter(manga -> !userCollectionIds.contains(manga.getId()))
                    .filter(manga -> !manga.getImageUrl().startsWith("https:"))
                    .collect(Collectors.toList());
        }

        return mangaDtoMapper.fromMangaEntityListToMangaDtoList(filteredMangas);
    }



    @Override
    public MangaEntity findMangaByTittle(String name) {
        return mangaRepository.findByTitleIgnoreCase(name).orElse(null);
    }

    @Override
    public List<MangaDto> findMangasReleasedInLast30Days() {
        LocalDate thirtyDaysAgo = LocalDate.now().minusDays(30);
        List<MangaEntity> mangas = mangaRepository.findByReleaseDateAfter(thirtyDaysAgo);

        List<MangaEntity> filteredMangas = mangas.stream()
                .filter(manga -> !manga.getImageUrl().startsWith("https:"))
                .collect(Collectors.toList());

        return filteredMangas.stream()
                .map(manga -> new MangaDto(
                        manga.getId(),
                        manga.getTitle(),
                        manga.getAuthor(),
                        manga.getGenre().getId(),
                        manga.getChapters(),
                        manga.isCompleted(),
                        manga.getImageUrl(),
                        manga.getSynopsis(),
                        manga.getReleaseDate()
                ))
                .collect(Collectors.toList());
    }



    private List<Long> uuidToLong(UUID uuid) {
        if (uuid == null) {
            return Arrays.asList(null, null);
        }
        return Arrays.asList(uuid.getMostSignificantBits(), uuid.getLeastSignificantBits());
    }

    public UUID longToUuid(Long id) {
        return UUID.nameUUIDFromBytes(Long.toString(id).getBytes());
    }
}
