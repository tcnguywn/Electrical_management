package com.dev.electricity.dto.request.UsageHistory;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class UsageHistoryUpdateRequest {
    long unitsUsed;
}
