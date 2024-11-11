package com.dev.electricity.dto.response;


import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TierConfigResponse {
    long tierId;
    long minVal;
    long maxVal;
    double pricePerUnit;
}