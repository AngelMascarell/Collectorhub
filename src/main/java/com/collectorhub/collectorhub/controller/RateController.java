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

    @PostMapping("/new")
    public ResponseEntity<RateResponse> createRate(@Valid @RequestBody RateRequest rateRequest) {
        RateResponse createdRate = rateDtoMapper.fromRateDtoToRateResponse(rateService.createRate(rateDtoMapper.fromRateRequestToRateDto(rateRequest)));
        return new ResponseEntity<>(createdRate, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RateResponse> getRateById(@PathVariable UUID id) {
        RateResponse rate = rateDtoMapper.fromRateDtoToRateResponse(rateService.getRateById(id));
        return ResponseEntity.ok(rate);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<RateResponse>> getAllRates() {
        List<RateResponse> allRates = rateDtoMapper.fromRateDtoListToRateResponseList(rateService.getAllRates());
        return ResponseEntity.ok(allRates);
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

}
