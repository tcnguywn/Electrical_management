package com.dev.electricity.mapper;

import com.dev.electricity.dto.response.UsageHistoryResponse;
import com.dev.electricity.entity.UsageHistory;
import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

@Mapper(componentModel = "spring")
public interface UsageHistoryMapper {
    UsageHistoryResponse toUsageHistoryResponse(UsageHistory usageHistory);
}