package com.dev.electricity.service;


import com.dev.electricity.dto.request.TierConfig.TierConfigCreationRequest;
import com.dev.electricity.dto.request.TierConfig.TierConfigUpdateRequest;
import com.dev.electricity.dto.response.TierConfigResponse;
import com.dev.electricity.entity.TierConfig;
import com.dev.electricity.exception.AppException;
import com.dev.electricity.exception.ErrorCode;
import com.dev.electricity.mapper.TierConfigMapper;
import com.dev.electricity.repository.TierConfigRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TierConfigService {
    TierConfigRepository tierConfigRepository;
    TierConfigMapper tierConfigMapper;

    public TierConfigResponse createTierConfig(TierConfigCreationRequest request) {
        if(tierConfigRepository.existsByMinValLessThanEqualAndMaxValGreaterThanEqual(request.getMaxVal(), request.getMinVal())) {
            throw new AppException(ErrorCode.TIER_EXISTED);
        }
        TierConfig tierConfig = tierConfigMapper.toTierConfig(request);

        return tierConfigMapper.toTierConfigResponse(tierConfigRepository.save(tierConfig));
    }

    public TierConfigResponse updateTierConfig(Long idTier,TierConfigUpdateRequest request) {
        TierConfig tierConfig = tierConfigRepository.findById(idTier)
                .orElseThrow(() -> new RuntimeException("Tier has not existed"));
        tierConfigMapper.updateTier(tierConfig, request);
        return tierConfigMapper.toTierConfigResponse(tierConfigRepository.save(tierConfig));
    }

    public List<TierConfigResponse> getAllTierConfigs() {
        return tierConfigRepository.findAll().stream()
                .map(tierConfigMapper :: toTierConfigResponse).toList();
    }

    public TierConfigResponse getTierConfigById(Long idTier) {
        return tierConfigMapper.toTierConfigResponse(tierConfigRepository.findById(idTier)
            .orElseThrow(() -> new RuntimeException("Tier has not existed")));
    }
     public void deleteTierConfigById(Long idTier) {
         tierConfigRepository.deleteById(idTier);
     }
     public void deleteAllTierConfigs() {
         tierConfigRepository.deleteAll();
     }
}
