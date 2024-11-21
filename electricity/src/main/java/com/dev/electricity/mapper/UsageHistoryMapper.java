package com.dev.electricity.mapper;

import com.dev.electricity.dto.request.UsageHistory.UsageHistoryUpdateRequest;
import com.dev.electricity.dto.response.UsageHistoryResponse;
import com.dev.electricity.entity.UsageHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.jpa.repository.JpaRepository;

@Mapper(componentModel = "spring")
public interface UsageHistoryMapper {

    @Mapping(source = "monthUsage", target = "monthUsage") // Ánh xạ rõ ràng
    UsageHistoryResponse toUsageHistoryResponse(UsageHistory usageHistory);

    @Mapping(target = "user", ignore = true) // User sẽ được xử lý riêng trong service
    @Mapping(target = "idUsage", ignore = true) // Không thay đổi ID của UsageHistory
    @Mapping(source = "monthUsage", target = "monthUsage") // Đảm bảo `monthUsage` được cập nhật đúng
    void updateUsageHistory(@MappingTarget UsageHistory usageHistory, UsageHistoryUpdateRequest request);
}
