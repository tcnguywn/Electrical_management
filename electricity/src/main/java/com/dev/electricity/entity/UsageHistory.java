package com.dev.electricity.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class UsageHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idUsage;

    private LocalDate date;
    private long unitsUsed;
    private double amount;

}
