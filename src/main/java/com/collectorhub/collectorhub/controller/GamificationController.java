package com.collectorhub.collectorhub.controller;

import com.collectorhub.collectorhub.controller.request.GamificationRequest;
import com.collectorhub.collectorhub.controller.response.GamificationListResponse;
import com.collectorhub.collectorhub.controller.response.GamificationResponse;
import com.collectorhub.collectorhub.controller.response.MangaDexResponse;
import com.collectorhub.collectorhub.dto.GamificationDto;
import com.collectorhub.collectorhub.dto.mappers.AbstractGamificationDtoMapper;
import com.collectorhub.collectorhub.services.GamificationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/gamification")
public class GamificationController {

    private final String uploadDir = "uploads/";

    @Autowired
    private GamificationService gamificationService;

    @Autowired
    private AbstractGamificationDtoMapper gamificationDtoMapper;



    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + filename);

        Files.createDirectories(filePath.getParent());
        file.transferTo(filePath);

        String fileUrl = "/gamification/images/" + filename;
        return ResponseEntity.ok(fileUrl);
    }

    @GetMapping("/images/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable String filename) throws IOException {
        Path filePath = Paths.get(uploadDir + filename);

        if (!Files.exists(filePath)) {
            return ResponseEntity.notFound().build();
        }

        byte[] image = Files.readAllBytes(filePath);

        String contentType = Files.probeContentType(filePath);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(image);
    }


    @GetMapping("/covers/{id}")
    public ResponseEntity<byte[]> getCover(@PathVariable String id) {
        try {
            String imageUrl = "https://api.mangadex.org/cover/" + id;
            MangaDexResponse response = new RestTemplate().getForObject(imageUrl, MangaDexResponse.class);

            if (response == null || response.getData() == null || response.getData().getAttributes() == null) {
                return ResponseEntity.notFound().build();
            }

            String fileName = response.getData().getAttributes().getFileName();
            String localImagePath = uploadDir + fileName;

            byte[] image = new RestTemplate().getForObject(imageUrl, byte[].class);

            if (image == null) {
                return ResponseEntity.notFound().build();
            }

            Files.write(Paths.get(localImagePath), image);

            try {
                Files.readAllBytes(Paths.get(localImagePath));
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(500).body(null);
            }

            String contentType = Files.probeContentType(Paths.get(localImagePath));
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(image);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }


    @GetMapping("/getAll")
    public ResponseEntity<GamificationListResponse> getAllGamification() {
        List<GamificationDto> gamifications = gamificationService.getAllGamification();

        List<GamificationResponse> responses = gamifications.stream()
                .map(gamificationDtoMapper::fromGamificationDtoToGamificationResponse)
                .toList();

        GamificationListResponse listResponse = new GamificationListResponse();
        listResponse.setGamificationResponseList(responses);

        return ResponseEntity.ok(listResponse);
    }

    @PostMapping("/new")
    public ResponseEntity<GamificationResponse> createGamification(@Valid @RequestBody GamificationRequest gamificationRequest) {
        GamificationDto createdGamification = gamificationService.createGamification(gamificationRequest);
        GamificationResponse response = gamificationDtoMapper.fromGamificationDtoToGamificationResponse(createdGamification);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GamificationResponse> getGamificationById(@PathVariable UUID id) {
        GamificationDto gamificationDto = gamificationService.getGamificationById(id)
                .orElseThrow(() -> new RuntimeException("Gamification not found"));
        GamificationResponse response = gamificationDtoMapper.fromGamificationDtoToGamificationResponse(gamificationDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGamification(@PathVariable UUID id) {
        gamificationService.deleteGamification(id);
        return ResponseEntity.noContent().build();
    }

    /*
    @PutMapping("/{id}")
    public ResponseEntity<GamificationResponse> updateGamification(@PathVariable UUID id,
                                                                   @Valid @RequestBody GamificationRequest gamificationRequest) {
        GamificationDto updatedGamification = gamificationService.updateGamification(id, gamificationRequest);
        GamificationResponse response = gamificationDtoMapper.fromGamificationDtoToGamificationResponse(updatedGamification);
        return ResponseEntity.ok(response);
    }
     */
}
