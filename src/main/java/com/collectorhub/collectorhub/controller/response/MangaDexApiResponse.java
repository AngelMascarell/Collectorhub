package com.collectorhub.collectorhub.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

@Validated
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MangaDexApiResponse {

    private String result;
    private String response;
    private MangaData data;  // Renombrado a MangaData para mayor claridad
    private int limit;
    private int offset;
    private int total;

    @Validated
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    public static class MangaData {
        private String id;
        private String type;
        private MangaAttributes attributes;
        private List<Relationship> relationships;
    }

    @Validated
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    public static class MangaAttributes {
        private Map<String, String> title;       // Cambiado a Map para la clave del idioma
        private List<Map<String, String>> altTitles;
        private Map<String, String> description; // Cambiado a Map
        private boolean isLocked;
        private Map<String, String> links;
        private String originalLanguage;
        private String lastVolume;
        private String lastChapter;
        private String publicationDemographic;
        private String status;
        private int year;
        private String contentRating;
        private boolean chapterNumbersResetOnNewVolume;
        private List<String> availableTranslatedLanguages;
        private String latestUploadedChapter;
        private List<Tag> tags;
        private String state;
        private int version;
        private String createdAt;
        private String updatedAt;
    }

    @Validated
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    public static class Tag {
        private String id;
        private String type;
        private TagAttributes attributes;
        private List<Relationship> relationships;
    }

    @Validated
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    public static class TagAttributes {
        private Map<String, String> name;
        private Map<String, String> description;
        private String group;
        private int version;
    }

    @Validated
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    public static class Relationship {
        private String id;
        private String type;
        private String related;
        private Object attributes;
    }
}