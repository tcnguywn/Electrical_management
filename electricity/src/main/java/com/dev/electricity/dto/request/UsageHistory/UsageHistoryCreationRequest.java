package com.dev.electricity.dto.request.UsageHistory;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.YearMonth;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class UsageHistoryCreationRequest {
    YearMonth monthUsage;
    long unitsUsed;
    long idUser;
}
