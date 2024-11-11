package com.dev.electricity.dto.request.UsageHistory;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class UsageHistoryCreationRequest {
    LocalDate date;
    long unitsUsed;
}
