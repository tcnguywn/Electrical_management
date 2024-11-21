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
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/usage-history")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UsageHistoryController {

    UsageHistoryService usageHistoryService;

    @PostMapping
    public ApiResponse<UsageHistoryResponse> createUsageHistory(@RequestBody UsageHistoryCreationRequest request) {
        log.info("Creating usage history for user with ID: {}", request.getIdUser());

        UsageHistoryResponse response = usageHistoryService.createUsageHistory(request);

        return ApiResponse.<UsageHistoryResponse>builder()
                .code(1000)
                .message("Usage history created successfully")
                .result(response)
                .build();
    }

    @PutMapping("/{idUser}")
    public ApiResponse<UsageHistoryResponse> updateUsageHistory(
            @PathVariable long idUser,
            @RequestBody UsageHistoryUpdateRequest request) {
        log.info("Updating usage history for user with ID: {}", idUser);

        UsageHistoryResponse response = usageHistoryService.updateUsageHistory(idUser, request);

        return ApiResponse.<UsageHistoryResponse>builder()
                .code(1000)
                .message("Usage history updated successfully")
                .result(response)
                .build();
    }

    @GetMapping
    public ApiResponse<List<UsageHistoryResponse>> getAllUsageHistory() {
        log.info("Fetching all usage histories");

        List<UsageHistoryResponse> responses = usageHistoryService.getAllUsageHistories();

        return ApiResponse.<List<UsageHistoryResponse>>builder()
                .code(1000)
                .message("Fetched all usage histories successfully")
                .result(responses)
                .build();
    }

    @GetMapping("/month/{monthUsage}")
    public ApiResponse<UsageHistoryResponse> getUsageHistoryByMonth(@PathVariable YearMonth monthUsage) {
        log.info("Fetching usage history for month: {}", monthUsage);

        UsageHistoryResponse response = usageHistoryService.getUsageHistoryByMonth(monthUsage);

        return ApiResponse.<UsageHistoryResponse>builder()
                .code(1000)
                .message("Fetched usage history for month successfully")
                .result(response)
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<UsageHistoryResponse> getUsageHistoryById(@PathVariable("id") Long id) {
        log.info("Fetching usage history by ID: {}", id);

        UsageHistoryResponse response = usageHistoryService.getUsageHistoryById(id);

        return ApiResponse.<UsageHistoryResponse>builder()
                .code(1000)
                .message("Fetched usage history by ID successfully")
                .result(response)
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteUsageHistoryById(@PathVariable("id") Long idUsage) {
        log.info("Deleting usage history by ID: {}", idUsage);

        usageHistoryService.deleteUsageHistory(idUsage);

        return ApiResponse.<String>builder()
                .code(1000)
                .message("Usage history deleted successfully")
                .result("Usage history at ID " + idUsage + " has been deleted")
                .build();
    }

    @DeleteMapping
    public ApiResponse<String> deleteAllUsageHistories() {
        log.info("Deleting all usage histories");

        usageHistoryService.deleteAllUsageHistories();

        return ApiResponse.<String>builder()
                .code(1000)
                .message("All usage histories deleted successfully")
                .result("All usage histories have been deleted")
                .build();
    }
}
