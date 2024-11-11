package com.dev.electricity.mapper;

import com.dev.electricity.dto.request.TierConfig.TierConfigCreationRequest;
import com.dev.electricity.dto.request.TierConfig.TierConfigUpdateRequest;
import com.dev.electricity.dto.response.TierConfigResponse;
import com.dev.electricity.entity.TierConfig;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TierConfigMapper {
    TierConfig toTierConfig(TierConfigCreationRequest request);

    TierConfigResponse toTierConfigResponse(TierConfig tierConfig);

    void updateTier(@MappingTarget TierConfig user, TierConfigUpdateRequest request);
}
