package com.collectorhub.collectorhub.services.impl;

import com.collectorhub.collectorhub.controller.request.MangaListRequest;
import com.collectorhub.collectorhub.database.entities.MangaEntity;
import com.collectorhub.collectorhub.database.entities.MangaListEntity;
import com.collectorhub.collectorhub.database.repositories.MangaListRepository;
import com.collectorhub.collectorhub.database.repositories.MangaRepository;
import com.collectorhub.collectorhub.dto.MangaDto;
import com.collectorhub.collectorhub.dto.MangaListDto;
import com.collectorhub.collectorhub.dto.mappers.AbstractMangaDtoMapper;
import com.collectorhub.collectorhub.dto.mappers.AbstractMangaListDtoMapper;
import com.collectorhub.collectorhub.services.MangaListService;
import com.collectorhub.collectorhub.services.MangaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MangaListServiceImpl implements MangaListService {

    @Autowired
    private MangaListRepository mangaListRepository;

    @Autowired
    private MangaRepository mangaRepository;

    @Autowired
    private AbstractMangaListDtoMapper mangaListDtoMapper;

    @Autowired
    private AbstractMangaDtoMapper mangaDtoMapper;

    @Autowired
    private MangaService mangaService;


    public MangaListDto crearListaDeMangas(MangaListDto mangaListDto) {

        MangaListEntity nuevaLista = new MangaListEntity();
        nuevaLista.setName(mangaListDto.getName());
        nuevaLista.setDescription(mangaListDto.getDescription());

        // Obtén los IDs de manga en formato Long
        List<Long> mangaIds = mangaListDto.getMangas().stream()
                .map(MangaDto::getId) // Asumiendo que getId devuelve Long
                .collect(Collectors.toList());

        // Buscar las entidades Manga utilizando los Longs
        List<MangaEntity> mangas = mangaRepository.findAllByIdIn(mangaIds); // Asegúrate de tener un método que acepte una lista de Longs

        // Establecer las mangas en la nueva lista
        nuevaLista.setMangas(mangas);

        // Guardar la nueva lista en la base de datos y convertirla a DTO
        return mangaListDtoMapper.fromMangaListEntityToMangaListDto(mangaListRepository.save(nuevaLista));
    }




    public List<MangaDto> obtenerMangasDeLista(UUID listaId) {
        MangaListEntity lista = mangaListRepository.findById(listaId)
                .orElseThrow(() -> new EntityNotFoundException("Lista no encontrada"));
        return mangaDtoMapper.fromMangaEntityListToMangaDtoList(lista.getMangas());
    }

    @Override
    public MangaListDto fromMangaListRequestToMangaListDto(MangaListRequest request) {
        MangaListDto dto = new MangaListDto();
        dto.setName(request.getListName());
        dto.setDescription(request.getDescription());



        // Ahora buscar mangas por UUID
        List<MangaDto> mangaDtos = mangaService.findMangasByIds(request.getMangaIds());
        dto.setMangas(mangaDtos); // Asignar la lista de MangaDto

        return dto;
    }


    public UUID longToUuid(Long id) {
        if (id == null) {
            return null; // Manejo de nulos
        }

        // Crear un ByteBuffer de 16 bytes para almacenar el UUID
        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
        byteBuffer.putLong(id); // El primer long representa el most-significant bits
        byteBuffer.putLong(0);   // El segundo long representa el least-significant bits como cero o un valor predeterminado

        // Crear un UUID a partir del buffer
        return new UUID(byteBuffer.getLong(0), byteBuffer.getLong(8));
    }


}

