package com.dev.electricity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TierConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long tierId;

    private long minVal;
    private long maxVal;
    private double pricePerUnit;

}
