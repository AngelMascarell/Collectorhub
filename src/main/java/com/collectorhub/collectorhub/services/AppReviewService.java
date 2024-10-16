package com.collectorhub.collectorhub.services;

import com.collectorhub.collectorhub.dto.AppReviewDto;
import com.collectorhub.collectorhub.dto.RateDto;

import java.util.List;
import java.util.UUID;

public interface AppReviewService {

    public AppReviewDto createReview(AppReviewDto appReviewDto);

    public AppReviewDto updateReview(AppReviewDto appReviewDto, UUID id);

    public void deleteReview(UUID id);

    public AppReviewDto getReviewById(UUID id);

    public List<AppReviewDto> getAllReviews();

    public List<AppReviewDto> getAllReviewsByUserId(Long userId);

    public long countAllReviews();

    public double AverageAllReviews();
}
