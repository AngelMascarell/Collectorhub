package com.collectorhub.collectorhub.database.entities;

import com.collectorhub.collectorhub.database.BaseEntity;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "manga_lista")
@Transactional
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MangaListEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String description;

    @ManyToMany
    @JoinTable(
            name = "manga_manga_lista",
            joinColumns = @JoinColumn(name = "lista_id"),
            inverseJoinColumns = @JoinColumn(name = "manga_id"))
    private List<MangaEntity> mangas = new ArrayList<>();

}
