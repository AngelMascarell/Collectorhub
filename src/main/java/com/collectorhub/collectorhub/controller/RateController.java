package com.collectorhub.collectorhub.controller;

import com.collectorhub.collectorhub.controller.request.RateRequest;
import com.collectorhub.collectorhub.controller.response.RateResponse;
import com.collectorhub.collectorhub.dto.mappers.AbstractRateDtoMapper;
import com.collectorhub.collectorhub.services.RateService;
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
@RequestMapping("/rate")
public class RateController {

    @Autowired
    private RateService rateService;

    @Autowired
    private AbstractRateDtoMapper rateDtoMapper;

    @PostMapping
    public ResponseEntity<RateResponse> createRate(@Valid @RequestBody RateRequest rateRequest) {
        RateResponse createdManga = rateDtoMapper.fromRateDtoToRateResponse(rateService.createRate(rateDtoMapper.fromRateRequestToRateDto(rateRequest)));
        return new ResponseEntity<>(createdManga, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RateResponse> getRateById(@PathVariable UUID id) {
        RateResponse manga = rateDtoMapper.fromRateDtoToRateResponse(rateService.getRateById(id));
        return ResponseEntity.ok(manga);
    }

    @GetMapping
    public ResponseEntity<List<RateResponse>> getAllRates() {
        List<RateResponse> allMangas = rateDtoMapper.fromRateDtoListToRateResponseList(rateService.getAllRates());
        return ResponseEntity.ok(allMangas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RateResponse> updateManga(@PathVariable UUID id, @RequestBody RateRequest rateRequest) {
        RateResponse updatedManga = rateDtoMapper.fromRateDtoToRateResponse(rateService.updateRate(rateDtoMapper.fromRateRequestToRateDto(rateRequest), id));
        return ResponseEntity.ok(updatedManga);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManga(@PathVariable UUID id) {
        rateService.deleteRate(id);
        return ResponseEntity.noContent().build();
    }

}
