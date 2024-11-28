package com.collectorhub.collectorhub.controller;

import com.collectorhub.collectorhub.controller.request.RateRequest;
import com.collectorhub.collectorhub.controller.response.RateResponse;
import com.collectorhub.collectorhub.controller.response.RateResponseList;
import com.collectorhub.collectorhub.database.entities.UserEntity;
import com.collectorhub.collectorhub.dto.RateDto;
import com.collectorhub.collectorhub.dto.mappers.AbstractRateDtoMapper;
import com.collectorhub.collectorhub.services.RateService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/rate")
public class RateController {


    @Autowired
    private RateService rateService;

    @Autowired
    private AbstractRateDtoMapper rateDtoMapper;

    @PostMapping("/new")
    public ResponseEntity<RateResponse> createRate(@AuthenticationPrincipal UserEntity user, @RequestBody @Valid RateDto rateDto) {
        rateDto.setUserId(user.getId());
        rateDto.setDate(LocalDate.now());

        RateDto createdRate = rateService.createRate(rateDto);

        return ResponseEntity.ok(rateDtoMapper.fromRateDtoToRateResponse(createdRate));
    }

    @GetMapping("/user-review/{mangaId}")
    public ResponseEntity<Boolean> getUserReview(@PathVariable Long mangaId, @AuthenticationPrincipal UserEntity user) {
        boolean hasReviewed = rateService.getReviewByUserAndManga(mangaId, user.getId());

        return ResponseEntity.ok(hasReviewed);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RateResponse> getRateById(@PathVariable UUID id) {
        RateResponse rate = rateDtoMapper.fromRateDtoToRateResponse(rateService.getRateById(id));
        return ResponseEntity.ok(rate);
    }

    @GetMapping("/getAll")
    public ResponseEntity<RateResponseList> getAllRates() {
        List<RateResponse> allRates = rateDtoMapper.fromRateDtoListToRateResponseList(rateService.getAllRates());
        RateResponseList responseList = new RateResponseList(allRates);
        return ResponseEntity.ok(responseList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RateResponse> updateRate(@PathVariable UUID id, @RequestBody RateRequest rateRequest) {
        RateResponse updatedRate = rateDtoMapper.fromRateDtoToRateResponse(rateService.updateRate(rateDtoMapper.fromRateRequestToRateDto(rateRequest), id));
        return ResponseEntity.ok(updatedRate);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRate(@PathVariable UUID id) {
        rateService.deleteRate(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/manga/{mangaId}")
    public ResponseEntity<RateResponseList> getRatesByMangaId(@PathVariable Long mangaId) {
        List<RateDto> rateDtoList = rateService.getAllRatesByMangaId(mangaId);
        List<RateResponse> responseList = rateDtoMapper.fromRateDtoListToRateResponseList(rateDtoList);
        RateResponseList rateResponseList = new RateResponseList(responseList);
        return ResponseEntity.ok(rateResponseList);
    }

    @GetMapping("/manga/{mangaId}/average")
    public ResponseEntity<Integer> getAverageRateByMangaId(@PathVariable Long mangaId) {
        int averageRate = rateService.getAverageRateByMangaId(mangaId);
        return ResponseEntity.ok(averageRate);
    }

}
