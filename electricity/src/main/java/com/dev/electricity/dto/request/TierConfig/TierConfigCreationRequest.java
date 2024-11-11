package com.dev.electricity.dto.request.TierConfig;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TierConfigCreationRequest {
    long minVal;
    long maxVal;
    double pricePerUnit;
}
