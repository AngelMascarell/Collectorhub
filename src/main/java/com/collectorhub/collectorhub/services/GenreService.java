package com.collectorhub.collectorhub.services;

import com.collectorhub.collectorhub.database.entities.GenreEntity;
import com.collectorhub.collectorhub.dto.GenreDto;

public interface GenreService {

    public GenreDto findByName(String name);

}
