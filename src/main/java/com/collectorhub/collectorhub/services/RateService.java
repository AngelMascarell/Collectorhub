package com.collectorhub.collectorhub.services;

import com.collectorhub.collectorhub.dto.RateDto;

import java.util.List;
import java.util.UUID;

public interface RateService {

    public RateDto createRate(RateDto rateDto);

    public RateDto updateRate(RateDto rateDto, UUID id);

    public void deleteRate(UUID id);

    public RateDto getRateById(UUID id);

    public List<RateDto> getAllRates();

    public List<RateDto> getAllRatesByMangaId(Long mangaId);

    public int getAverageRateByMangaId(Long mangaId);
}
