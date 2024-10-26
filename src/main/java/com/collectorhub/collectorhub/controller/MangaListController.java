package com.collectorhub.collectorhub.controller;

import com.collectorhub.collectorhub.controller.request.MangaListRequest;
import com.collectorhub.collectorhub.controller.response.ObtainMangasResponse;
import com.collectorhub.collectorhub.dto.MangaDto;
import com.collectorhub.collectorhub.dto.MangaListDto;
import com.collectorhub.collectorhub.dto.mappers.AbstractMangaDtoMapper;
import com.collectorhub.collectorhub.dto.mappers.AbstractMangaListDtoMapper;
import com.collectorhub.collectorhub.services.MangaListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/mangaList")
public class MangaListController {

    @Autowired
    private AbstractMangaListDtoMapper mangaListDtoMapper;

    @Autowired
    private MangaListService mangaListService;

    @Autowired
    private AbstractMangaDtoMapper mangaDtoMapper;


    @PostMapping("/crear")
    public ResponseEntity<HttpStatus> crearListaDeMangas(@RequestBody MangaListRequest request) {
        MangaListDto list = mangaListService.crearListaDeMangas(mangaListService.fromMangaListRequestToMangaListDto(request));
        //MangaListResponse response = mangaListDtoMapper.fromMangaListDtoToMangaListResponse(list);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/{listaId}/mangas")
    public ResponseEntity<ObtainMangasResponse> obtenerMangasDeLista(@PathVariable UUID listaId) {
        List<MangaDto> mangas = mangaListService.obtenerMangasDeLista(listaId);
        ObtainMangasResponse response = mangaDtoMapper.fromMangaDtoListToObtainMangaResponse(mangas);
        return ResponseEntity.ok(response);
    }

}
