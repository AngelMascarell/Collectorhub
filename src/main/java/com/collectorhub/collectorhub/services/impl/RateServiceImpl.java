package com.collectorhub.collectorhub.services.impl;

import com.collectorhub.collectorhub.database.entities.MangaEntity;
import com.collectorhub.collectorhub.database.entities.UserEntity;
import com.collectorhub.collectorhub.database.repositories.RateRepository;
import com.collectorhub.collectorhub.dto.RateDto;
import com.collectorhub.collectorhub.dto.mappers.AbstractRateDtoMapper;
import com.collectorhub.collectorhub.services.RateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private AbstractRateDtoMapper rateDtoMapper;

        @Override
    public List<RateDto> findByUser(UserEntity user) {
        return rateDtoMapper.fromRateEntityListToRateDtoList(rateRepository.findByUser(user));
    }

    @Override
    public List<RateDto> findByManga(MangaEntity manga) {
        return rateDtoMapper.fromRateEntityListToRateDtoList(rateRepository.findByManga(manga));
    }

}
