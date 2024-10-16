package com.collectorhub.collectorhub.services.impl;

import com.collectorhub.collectorhub.database.entities.AppReviewEntity;
import com.collectorhub.collectorhub.database.entities.UserEntity;
import com.collectorhub.collectorhub.database.repositories.AppReviewRepository;
import com.collectorhub.collectorhub.database.repositories.UserRepository;
import com.collectorhub.collectorhub.dto.AppReviewDto;
import com.collectorhub.collectorhub.dto.mappers.AbstractAppReviewDtoMapper;
import com.collectorhub.collectorhub.services.AppReviewService;
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
public class AppReviewServiceImpl implements AppReviewService {

    @Autowired
    private AppReviewRepository appReviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AbstractAppReviewDtoMapper appReviewDtoMapper;

    @Override
    public AppReviewDto createReview(AppReviewDto appReviewDto) {
        AppReviewEntity appReviewEntity = appReviewDtoMapper.fromAppReviewDtoToAppReviewEntity(appReviewDto);

        AppReviewEntity savedReviewEntity = appReviewRepository.save(appReviewEntity);

        return appReviewDtoMapper.fromAppReviewEntityToAppReviewDto(savedReviewEntity);
    }

    @Override
    public AppReviewDto updateReview(AppReviewDto appReviewDto, UUID id) {
        AppReviewEntity existingAppReviewEntity = appReviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review not found with id: " + id));

        existingAppReviewEntity.setRate(appReviewDto.getRate());
        existingAppReviewEntity.setComment(appReviewDto.getComment());

        UserEntity userEntity = userRepository.findById(appReviewDto.getUserId());
        existingAppReviewEntity.setUser(userEntity);

        AppReviewEntity updatedReviewEntity = appReviewRepository.save(existingAppReviewEntity);

        return appReviewDtoMapper.fromAppReviewEntityToAppReviewDto(updatedReviewEntity);
    }

    @Override
    public void deleteReview(UUID id) {
        if (!appReviewRepository.existsById(id)) {
            throw new EntityNotFoundException("Review not found with id: " + id);
        }
        appReviewRepository.deleteById(id);
    }

    @Override
    public AppReviewDto getReviewById(UUID id) {
        AppReviewEntity reviewEntity = appReviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rate not found with id: " + id));

        return appReviewDtoMapper.fromAppReviewEntityToAppReviewDto(reviewEntity);
    }

    @Override
    public List<AppReviewDto> getAllReviews() {
        List<AppReviewEntity> allReviews = appReviewRepository.findAll();

        return appReviewDtoMapper.fromAppReviewEntityListToAppReviewDtoList(allReviews);
    }

    @Override
    public List<AppReviewDto> getAllReviewsByUserId(Long userId) {
        List<AppReviewEntity> ratesForUser = appReviewRepository.findByUserId(userId);

        return appReviewDtoMapper.fromAppReviewEntityListToAppReviewDtoList(ratesForUser);
    }

    @Override
    public long countAllReviews() {
        return appReviewRepository.count();
    }

    @Override
    public double AverageAllReviews() {
        List<AppReviewEntity> entities = appReviewRepository.findAll();
        double suma = 0.0;
        for(AppReviewEntity reviewEntity : entities) {
            suma = suma + reviewEntity.getRate();
        }
        return suma / countAllReviews();
    }
}
