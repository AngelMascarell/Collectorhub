package com.collectorhub.collectorhub.services.impl;

import com.collectorhub.collectorhub.controller.response.MangaDexApiResponse;
import com.collectorhub.collectorhub.database.entities.MangaEntity;
import com.collectorhub.collectorhub.database.repositories.GenreRepository;
import com.collectorhub.collectorhub.database.repositories.MangaRepository;
import com.collectorhub.collectorhub.dto.MangaDexResponseDto;
import com.collectorhub.collectorhub.dto.mappers.AbstractMangaDtoMapper;
import com.collectorhub.collectorhub.services.MangaDexService;
import org.aspectj.asm.internal.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MangaDexServiceImpl implements MangaDexService {

    private static final String BASE_URL = "https://api.mangadex.org";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AbstractMangaDtoMapper mangaDtoMapper;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private MangaRepository mangaRepository;

    public MangaDexResponseDto getMangaById(String mangaId) {
        String url = BASE_URL + "/manga/" + mangaId;

        // Llamada a la API de MangaDex
        try {
            MangaDexApiResponse response = restTemplate.getForObject(url, MangaDexApiResponse.class);

            // Asegúrate de manejar correctamente la respuesta
            if (response == null || response.getData() == null) {
                return null;
            }

            // Accede directamente al objeto data
            MangaDexApiResponse.MangaData mangaData = response.getData();

            // Transformar a MangaDexResponseDto
            MangaDexResponseDto manga = new MangaDexResponseDto();
            manga.setId(mangaData.getId());
            manga.setTitle(mangaData.getAttributes().getTitle().get("en")); // Asegúrate de que "en" esté presente
            manga.setDescription(mangaData.getAttributes().getDescription().get("en"));
            manga.setCompleted("completed".equalsIgnoreCase(mangaData.getAttributes().getStatus()));
            manga.setPublicationDemographic(mangaData.getAttributes().getPublicationDemographic());

            manga.setSynopsis(mangaData.getAttributes().getDescription().toString());
            manga.setReleaseDate(LocalDate.parse(mangaData.getAttributes().getCreatedAt()));


            // Manejar los géneros
            manga.setGenres(mangaData.getAttributes().getTags().stream()
                    .map(tag -> tag.getAttributes().getName().get("en")) // Nombre de los géneros
                    .collect(Collectors.toList()));

            String lastChapter = mangaData.getAttributes().getLastChapter();
            manga.setChapters(lastChapter != null && !lastChapter.isEmpty()
                    ? Integer.parseInt(lastChapter)
                    : 0);


            // Llamada al método para extraer la URL de la imagen
            List<MangaDexApiResponse.Relationship> relationships = mangaData.getRelationships();
            if (relationships != null) {
                manga.setImageUrl(extractImageUrl(relationships));
            }

            return manga;
        } catch (HttpClientErrorException e) {
            System.err.println("Error al llamar a la API de MangaDex: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("Se produjo un error inesperado: " + e.getMessage());
            return null;
        }
    }



    private String extractImageUrl(List<MangaDexApiResponse.Relationship> relationships) {
        return relationships.stream()
                .filter(rel -> "cover_art".equalsIgnoreCase(rel.getType()))
                .map(MangaDexApiResponse.Relationship::getId)
                .findFirst()
                .map(id -> "https://uploads.mangadex.org/covers/" + id + ".jpg")
                .orElse(null);
    }

    public MangaEntity saveManga(String mangaId) {
        MangaDexResponseDto model = getMangaById(mangaId);
        MangaEntity mangaEntity = mangaDtoMapper.fromMangaDexResponseDtoToMangaEntity(model, genreRepository);
        return mangaRepository.save(mangaEntity);
    }

    public List<MangaDexResponseDto> searchMangas(int limit, int offset, String title) {
        String url = BASE_URL + "/manga";
        String requestUrl = url + "?limit=" + limit + "&offset=" + offset;

        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(requestUrl, String.class);
            String responseBody = responseEntity.getBody();

            System.out.println("Response Body: " + responseBody);

            MangaDexApiResponse response = restTemplate.getForObject(requestUrl, MangaDexApiResponse.class);

            if (response == null) {
                System.err.println("La respuesta de la API es nula.");
                return null;
            }

            if (response.getData() == null) {
                System.err.println("Los datos de la respuesta son nulos.");
                return null;
            }

            if (!(response.getData() instanceof List)) {
                System.err.println("Los datos no son una lista, tipo recibido: " + response.getData().getClass().getName());
                return null;
            }

            List<MangaDexApiResponse.MangaData> mangaList = (List<MangaDexApiResponse.MangaData>) response.getData();

            return mangaList.stream()
                    .map(mangaData -> {
                        try {
                            MangaDexResponseDto manga = new MangaDexResponseDto();
                            manga.setId(mangaData.getId());
                            manga.setTitle(mangaData.getAttributes().getTitle().get("en"));
                            manga.setDescription(mangaData.getAttributes().getDescription().get("en"));
                            manga.setCompleted("completed".equalsIgnoreCase(mangaData.getAttributes().getStatus()));
                            manga.setPublicationDemographic(mangaData.getAttributes().getPublicationDemographic());
                            manga.setSynopsis(mangaData.getAttributes().getDescription().toString());
                            manga.setReleaseDate(LocalDate.parse(mangaData.getAttributes().getCreatedAt()));
                            manga.setGenres(mangaData.getAttributes().getTags().stream()
                                    .map(tag -> tag.getAttributes().getName().get("en"))
                                    .collect(Collectors.toList()));
                            String lastChapter = mangaData.getAttributes().getLastChapter();
                            manga.setChapters(lastChapter != null && !lastChapter.isEmpty()
                                    ? Integer.parseInt(lastChapter)
                                    : 0);

                            List<MangaDexApiResponse.Relationship> relationships = mangaData.getRelationships();
                            if (relationships != null) {
                                manga.setImageUrl(extractImageUrl(relationships));
                            }
                            return manga;
                        } catch (Exception e) {
                            System.err.println("Error al mapear mangaData con ID " + mangaData.getId() + ": " + e.getMessage());
                            return null;
                        }
                    })
                    .collect(Collectors.toList());
        } catch (HttpClientErrorException e) {
            System.err.println("Error al llamar a la API de MangaDex: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("Se produjo un error inesperado: " + e.getMessage());
            return null;
        }
    }



}
