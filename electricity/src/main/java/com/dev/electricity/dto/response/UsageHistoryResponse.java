package com.dev.electricity.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsageHistoryResponse {
    Long idUsage;

    LocalDate date;
    long unitsUsed;
    double amount;
}
