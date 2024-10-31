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
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

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
                return null; // O lanza una excepción, dependiendo de tu lógica
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
            // Maneja la excepción adecuadamente
            System.err.println("Error al llamar a la API de MangaDex: " + e.getMessage());
            return null; // O lanza una excepción personalizada
        } catch (Exception e) {
            // Captura cualquier otra excepción que pueda ocurrir
            System.err.println("Se produjo un error inesperado: " + e.getMessage());
            return null; // O lanza una excepción personalizada
        }
    }



    private String extractImageUrl(List<MangaDexApiResponse.Relationship> relationships) {
        return relationships.stream()
                .filter(rel -> "cover_art".equalsIgnoreCase(rel.getType()))
                .map(MangaDexApiResponse.Relationship::getId)
                .findFirst()
                .map(id -> "https://uploads.mangadex.org/covers/" + id + ".jpg") // Formato de la imagen
                .orElse(null);
    }

    public MangaEntity saveManga(String mangaId) {
        MangaDexResponseDto model = getMangaById(mangaId);
        MangaEntity mangaEntity = mangaDtoMapper.fromMangaDexResponseDtoToMangaEntity(model, genreRepository);
        return mangaRepository.save(mangaEntity);
    }

}
