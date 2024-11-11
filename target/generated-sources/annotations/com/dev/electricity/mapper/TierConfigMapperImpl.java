package com.dev.electricity.mapper;

import com.dev.electricity.dto.request.TierConfig.TierConfigCreationRequest;
import com.dev.electricity.dto.request.TierConfig.TierConfigUpdateRequest;
import com.dev.electricity.dto.response.TierConfigResponse;
import com.dev.electricity.entity.TierConfig;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class TierConfigMapperImpl implements TierConfigMapper {

    @Override
    public TierConfig toTierConfig(TierConfigCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        TierConfig tierConfig = new TierConfig();

        tierConfig.setMinVal( request.getMinVal() );
        tierConfig.setMaxVal( request.getMaxVal() );
        tierConfig.setPricePerUnit( request.getPricePerUnit() );

        return tierConfig;
    }

    @Override
    public TierConfigResponse toTierConfigResponse(TierConfig tierConfig) {
        if ( tierConfig == null ) {
            return null;
        }

        TierConfigResponse.TierConfigResponseBuilder tierConfigResponse = TierConfigResponse.builder();

        tierConfigResponse.tierId( tierConfig.getTierId() );
        tierConfigResponse.minVal( tierConfig.getMinVal() );
        tierConfigResponse.maxVal( tierConfig.getMaxVal() );
        tierConfigResponse.pricePerUnit( tierConfig.getPricePerUnit() );

        return tierConfigResponse.build();
    }

    @Override
    public void updateTier(TierConfig user, TierConfigUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        user.setPricePerUnit( request.getPricePerUnit() );
    }
}
