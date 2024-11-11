package com.dev.electricity.service;

import com.dev.electricity.dto.request.UsageHistory.UsageHistoryCreationRequest;
import com.dev.electricity.dto.request.UsageHistory.UsageHistoryUpdateRequest;
import com.dev.electricity.dto.response.TierConfigResponse;
import com.dev.electricity.dto.response.UsageHistoryResponse;
import com.dev.electricity.entity.TierConfig;
import com.dev.electricity.entity.UsageHistory;
import com.dev.electricity.exception.AppException;
import com.dev.electricity.exception.ErrorCode;
import com.dev.electricity.mapper.UsageHistoryMapper;
import com.dev.electricity.repository.TierConfigRepository;
import com.dev.electricity.repository.UsageHistoryRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UsageHistoryService {
    TierConfigRepository tierConfigRepository;
    UsageHistoryRepository usageHistoryRepository;
    UsageHistoryMapper usageHistoryMapper;

    public UsageHistoryResponse createUsageHistory(UsageHistoryCreationRequest request) {
        double amount = calculateElectricityBill(request.getUnitsUsed());

        UsageHistory usageHistory = new UsageHistory();
        usageHistory.setDate(request.getDate());
        usageHistory.setUnitsUsed(request.getUnitsUsed());
        usageHistory.setAmount(amount);

        return usageHistoryMapper.toUsageHistoryResponse(usageHistoryRepository.save(usageHistory));
    }


    public List<UsageHistoryResponse> getAllUsageHistories() {
        return usageHistoryRepository.findAll().stream()
                .map(usageHistoryMapper :: toUsageHistoryResponse).toList();
    }

    public UsageHistoryResponse getUsageHistoryById(Long idUsage) {
        return usageHistoryMapper.toUsageHistoryResponse(usageHistoryRepository.findById(idUsage)
                .orElseThrow(() -> new RuntimeException("Usage history not found")));
    }

    public UsageHistoryResponse getUsageHistoryByDate(LocalDate date) {
        return usageHistoryMapper.toUsageHistoryResponse(usageHistoryRepository.findByDate(date));
    }

    public UsageHistoryResponse updateUsageHistory(LocalDate date, UsageHistoryUpdateRequest request) {
        UsageHistory usageHistory = usageHistoryRepository.findByDate(date);
        usageHistory.setUnitsUsed(request.getUnitsUsed());
        usageHistory.setAmount(calculateElectricityBill(request.getUnitsUsed()));

        return usageHistoryMapper.toUsageHistoryResponse(usageHistoryRepository.save(usageHistory));
    }


    public void deleteUsageHistory(Long idUsage) {
        usageHistoryRepository.deleteById(idUsage);
    }

    public void deleteAllUsageHistories() {
        usageHistoryRepository.deleteAll();
    }

    // Phương thức tính tiền điện dựa trên số điện sử dụng
    private double calculateElectricityBill(long unitsUsed) {
        List<TierConfig> tierConfigs = tierConfigRepository.findAll(Sort.by("minVal"));
        double amount = 0;

        for (TierConfig tier : tierConfigs) {
            if (unitsUsed > 0) {
                long unitsInTier = Math.min(unitsUsed, tier.getMaxVal() - tier.getMinVal() + 1);
                amount += unitsInTier * tier.getPricePerUnit();
                unitsUsed -= unitsInTier;
            } else {
                break;
            }
        }
        return amount;
    }
}