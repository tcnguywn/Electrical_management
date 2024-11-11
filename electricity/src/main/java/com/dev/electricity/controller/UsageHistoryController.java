package com.dev.electricity.controller;


import com.dev.electricity.dto.request.ApiResponse;
import com.dev.electricity.dto.request.TierConfig.TierConfigCreationRequest;
import com.dev.electricity.dto.request.UsageHistory.UsageHistoryCreationRequest;
import com.dev.electricity.dto.request.UsageHistory.UsageHistoryUpdateRequest;
import com.dev.electricity.dto.response.TierConfigResponse;
import com.dev.electricity.dto.response.UsageHistoryResponse;
import com.dev.electricity.service.UsageHistoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/usage-history")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UsageHistoryController {

    UsageHistoryService usageHistoryService;

    @PostMapping
    ApiResponse<UsageHistoryResponse> createUsageHistory(@RequestBody UsageHistoryCreationRequest request){
        ApiResponse<UsageHistoryResponse> apiResponse = new ApiResponse<>();

        apiResponse.setResult(usageHistoryService.createUsageHistory(request));

        return apiResponse;
    }

    @PutMapping("/date/{date}")
    public UsageHistoryResponse updateUsageHistorybyDate(@PathVariable LocalDate date, @RequestBody UsageHistoryUpdateRequest request){
        UsageHistoryResponse usageHistoryResponse = new UsageHistoryResponse();
        usageHistoryResponse = usageHistoryService.updateUsageHistory(date, request);
        return usageHistoryResponse;
    }

    @GetMapping
    List<UsageHistoryResponse> getAllUsageHistory(){
        return usageHistoryService.getAllUsageHistories();
    }
    @GetMapping("/date/{date}")
    UsageHistoryResponse getUsageHistoryByDate(@PathVariable LocalDate date){
        return usageHistoryService.getUsageHistoryByDate(date);
    }

    @GetMapping("/{id}")
    UsageHistoryResponse getUsageHistoryById(@PathVariable("id") Long id){
        return usageHistoryService.getUsageHistoryById(id);
    }

    @DeleteMapping("/{id}")
    String deleteUsageHistoryById(@PathVariable("id") Long idUsage){
        usageHistoryService.deleteUsageHistory(idUsage);
        return "Usage at " + idUsage + "history has been deleted";
    }
}
