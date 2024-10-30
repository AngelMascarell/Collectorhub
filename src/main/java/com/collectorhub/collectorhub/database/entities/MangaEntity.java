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
@Table(name = "manga")
@Transactional
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MangaEntity extends BaseEntity {

    @Column(nullable = false, name = "tittle", unique = true)
    private String title;

    @Column(nullable = false, name = "author")
    private String author;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private GenreEntity genre;

    @Column(name = "num_chapters")
    private int chapters;

    @Column(name = "is_completed")
    private boolean completed;

    @ManyToMany(mappedBy = "mangas")
    private List<UserEntity> propietarios;

    @ManyToMany(mappedBy = "mangas")
    private List<MangaListEntity> listas = new ArrayList<>();

    private String imageUrl;

}
