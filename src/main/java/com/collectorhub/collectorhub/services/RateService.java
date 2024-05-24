package com.collectorhub.collectorhub.services;

import com.collectorhub.collectorhub.database.entities.MangaEntity;
import com.collectorhub.collectorhub.database.entities.RateEntity;
import com.collectorhub.collectorhub.database.entities.UserEntity;
import com.collectorhub.collectorhub.dto.RateDto;

import java.util.List;

public interface RateService {

    public List<RateDto> findByUser(UserEntity user);

    public List<RateDto> findByManga(MangaEntity manga);

}
