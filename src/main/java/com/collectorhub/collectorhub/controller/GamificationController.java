package com.collectorhub.collectorhub.controller;

import com.collectorhub.collectorhub.controller.request.GamificationRequest;
import com.collectorhub.collectorhub.controller.response.GamificationListResponse;
import com.collectorhub.collectorhub.controller.response.GamificationResponse;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
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
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename(); // Evitar duplicados
        Path filePath = Paths.get(uploadDir + filename);

        Files.createDirectories(filePath.getParent()); // Crea el directorio si no existe
        file.transferTo(filePath); // Guarda el archivo en el servidor

        String fileUrl = "/gamification/images/" + filename; // URL de acceso a la imagen
        return ResponseEntity.ok(fileUrl);
    }

    @GetMapping("/images/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable String filename) throws IOException {
        // Define el directorio donde se encuentran las imágenes
        Path filePath = Paths.get(uploadDir + filename);

        // Verifica si el archivo existe
        if (!Files.exists(filePath)) {
            return ResponseEntity.notFound().build(); // Retorna 404 si la imagen no se encuentra
        }

        byte[] image = Files.readAllBytes(filePath);

        // Determina el tipo de contenido (por ejemplo, "image/jpeg", "image/png")
        String contentType = Files.probeContentType(filePath);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(image);
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
