package com.collectorhub.collectorhub.services.impl;

import com.collectorhub.collectorhub.database.entities.MangaEntity;
import com.collectorhub.collectorhub.database.entities.RateEntity;
import com.collectorhub.collectorhub.database.entities.UserEntity;
import com.collectorhub.collectorhub.database.repositories.MangaRepository;
import com.collectorhub.collectorhub.database.repositories.RateRepository;
import com.collectorhub.collectorhub.database.repositories.UserRepository;
import com.collectorhub.collectorhub.dto.RateDto;
import com.collectorhub.collectorhub.dto.mappers.AbstractRateDtoMapper;
import com.collectorhub.collectorhub.services.RateService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private MangaRepository mangaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AbstractRateDtoMapper rateDtoMapper;

    @Override
    public RateDto createRate(RateDto rateDto) {
        RateEntity rateEntity = rateDtoMapper.fromRateDtoToRateEntity(rateDto);

        RateEntity savedRateEntity = rateRepository.save(rateEntity);

        return rateDtoMapper.fromRateEntityToRateDto(savedRateEntity);
    }

    private static UUID convertLongToUUID(Long id) {
        return new UUID(0L, id);
    }

    @Override
    public RateDto updateRate(RateDto rateDto, UUID id) {
        RateEntity existingRate = rateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rate not found with id: " + id));

        existingRate.setRate(rateDto.getRate());
        existingRate.setComment(rateDto.getComment());

        MangaEntity mangaEntity = mangaRepository.findById(rateDto.getMangaId());
        existingRate.setManga(mangaEntity);

        UserEntity userEntity = userRepository.findById(rateDto.getUserId());
        existingRate.setUser(userEntity);

        RateEntity updatedRateEntity = rateRepository.save(existingRate);

        return rateDtoMapper.fromRateEntityToRateDto(updatedRateEntity);
    }

    @Override
    public void deleteRate(UUID id) {
        if (!rateRepository.existsById(id)) {
            throw new EntityNotFoundException("Rate not found with id: " + id);
        }
        rateRepository.deleteById(id);
    }

    @Override
    public RateDto getRateById(UUID id) {
        RateEntity rateEntity = rateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rate not found with id: " + id));

        return rateDtoMapper.fromRateEntityToRateDto(rateEntity);
    }

    @Override
    public List<RateDto> getAllRates() {
        List<RateEntity> allRates = rateRepository.findAll();

        return rateDtoMapper.fromRateEntityListToRateDtoList(allRates);
    }

    @Override
    public List<RateDto> getAllRatesByMangaId(Long mangaId) {
        List<RateEntity> ratesForManga = rateRepository.findByMangaId(mangaId);

        return rateDtoMapper.fromRateEntityListToRateDtoList(ratesForManga);
    }
}
