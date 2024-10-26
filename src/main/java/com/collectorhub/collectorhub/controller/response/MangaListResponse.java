package com.collectorhub.collectorhub.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Validated
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MangaListResponse implements Serializable {

    private String listName;
    private String description;
    private List<Long> mangaIds;

}
