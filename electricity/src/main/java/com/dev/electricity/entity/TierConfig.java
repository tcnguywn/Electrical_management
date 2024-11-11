package com.dev.electricity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
public class TierConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long tierId;

    private long minVal;
    private long maxVal;
    private double pricePerUnit;

}