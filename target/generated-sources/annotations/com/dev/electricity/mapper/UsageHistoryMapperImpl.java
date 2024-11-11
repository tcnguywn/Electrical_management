package com.dev.electricity.mapper;

import com.dev.electricity.dto.response.UsageHistoryResponse;
import com.dev.electricity.entity.UsageHistory;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class UsageHistoryMapperImpl implements UsageHistoryMapper {

    @Override
    public UsageHistoryResponse toUsageHistoryResponse(UsageHistory usageHistory) {
        if ( usageHistory == null ) {
            return null;
        }

        UsageHistoryResponse.UsageHistoryResponseBuilder usageHistoryResponse = UsageHistoryResponse.builder();

        usageHistoryResponse.idUsage( usageHistory.getIdUsage() );
        usageHistoryResponse.date( usageHistory.getDate() );
        usageHistoryResponse.unitsUsed( usageHistory.getUnitsUsed() );
        usageHistoryResponse.amount( usageHistory.getAmount() );

        return usageHistoryResponse.build();
    }
}
