package com.collectorhub.collectorhub.services;

import com.collectorhub.collectorhub.controller.request.GamificationRequest;
import com.collectorhub.collectorhub.controller.response.GamificationResponse;
import com.collectorhub.collectorhub.dto.GamificationConditionDto;
import com.collectorhub.collectorhub.dto.GamificationDto;
import com.collectorhub.collectorhub.dto.GenreDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GamificationService {

    public GamificationDto createGamification(GamificationRequest gamification);

    public Optional<GamificationDto> getGamificationById(UUID id);

    public List<GamificationDto> getAllGamification();

    public GamificationDto addConditionToGamification(UUID gamificationId, GamificationConditionDto condition);

    public boolean checkIfUserCanEarnGamification(Long userId, UUID gamificationId);

    public void deleteGamification(UUID id);

}
