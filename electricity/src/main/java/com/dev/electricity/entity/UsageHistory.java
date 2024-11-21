package com.dev.electricity.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.YearMonth;

@Entity
@Data
public class UsageHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idUsage;

    private YearMonth monthUsage;
    private long unitsUsed;
    private double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_User")
    private User user;

}
