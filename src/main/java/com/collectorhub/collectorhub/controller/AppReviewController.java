package com.collectorhub.collectorhub.controller;

import com.collectorhub.collectorhub.controller.request.AppReviewRequest;
import com.collectorhub.collectorhub.controller.response.AppReviewResponse;
import com.collectorhub.collectorhub.controller.response.AppReviewResponseList;
import com.collectorhub.collectorhub.dto.mappers.AbstractAppReviewDtoMapper;
import com.collectorhub.collectorhub.services.AppReviewService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/appReview")
public class AppReviewController {

    @Autowired
    private AppReviewService appReviewService;

    @Autowired
    private AbstractAppReviewDtoMapper appReviewDtoMapper;

    @PostMapping("/new")
    public ResponseEntity<AppReviewResponse> createRate(@Valid @RequestBody AppReviewRequest appReviewRequest) {
        AppReviewResponse createdReview = appReviewDtoMapper.fromAppReviewDtoToAppReviewResponse(appReviewService.createReview(appReviewDtoMapper.fromAppReviewRequestToAppReviewDto(appReviewRequest)));
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppReviewResponse> getReviewById(@PathVariable UUID id) {
        AppReviewResponse manga = appReviewDtoMapper.fromAppReviewDtoToAppReviewResponse(appReviewService.getReviewById(id));
        return ResponseEntity.ok(manga);
    }

    @GetMapping("/getAll")
    public ResponseEntity<AppReviewResponseList> getAllReviews() {
        List<AppReviewResponse> allReviews = appReviewDtoMapper.fromAppReviewDtoListToAppReviewResponseList(appReviewService.getAllReviews());
        AppReviewResponseList reviewResponseList = new AppReviewResponseList(allReviews);
        return ResponseEntity.ok(reviewResponseList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AppReviewResponse> updateReview(@PathVariable UUID id, @RequestBody AppReviewRequest appReviewRequest) {
        AppReviewResponse updatedManga = appReviewDtoMapper.fromAppReviewDtoToAppReviewResponse(appReviewService.updateReview(appReviewDtoMapper.fromAppReviewRequestToAppReviewDto(appReviewRequest), id));
        return ResponseEntity.ok(updatedManga);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable UUID id) {
        appReviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/countAll")
    public ResponseEntity<Long> getCountReviews() {
        long reviewsCount = appReviewService.countAllReviews();
        return ResponseEntity.ok(reviewsCount);
    }

    @GetMapping("/avgReviews")
    public ResponseEntity<Double> getAverageReviews() {
        Double reviewsAverage = appReviewService.AverageAllReviews();
        return ResponseEntity.ok(reviewsAverage);
    }

}
