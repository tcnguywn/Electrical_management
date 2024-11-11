package com.dev.electricity.dto.request.TierConfig;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TierConfigUpdateRequest {
    double pricePerUnit;
}
