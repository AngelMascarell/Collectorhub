package com.collectorhub.collectorhub.database.entities;

import com.collectorhub.collectorhub.database.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "Gamification_Condition")
@Transactional
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GamificationConditionEntity extends BaseEntity {

    private String type; // Tipo de condición, ej. "TASK_COMPLETED"
    private int threshold; // Número mínimo para cumplir la condición

}
