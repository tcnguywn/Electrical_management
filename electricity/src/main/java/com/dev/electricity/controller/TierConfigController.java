package com.dev.electricity.controller;


import com.dev.electricity.dto.request.ApiResponse;
import com.dev.electricity.dto.request.TierConfig.TierConfigCreationRequest;
import com.dev.electricity.dto.request.TierConfig.TierConfigUpdateRequest;
import com.dev.electricity.dto.response.TierConfigResponse;
import com.dev.electricity.service.TierConfigService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tier")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TierConfigController {
    TierConfigService tierConfigService;

    @PostMapping
    ApiResponse<TierConfigResponse> createTier(@RequestBody TierConfigCreationRequest request){
        ApiResponse<TierConfigResponse> apiResponse = new ApiResponse<>();

        apiResponse.setResult(tierConfigService.createTierConfig(request));

        return apiResponse;
    }

    @GetMapping
    List<TierConfigResponse> getAll(){
        return tierConfigService.getAllTierConfigs();
    }

    @PutMapping("/{tierId}")
    TierConfigResponse updateTier(@PathVariable long tierId, @RequestBody TierConfigUpdateRequest request){
        return tierConfigService.updateTierConfig(tierId, request);
    }

    @GetMapping ("/{tierId}")
    TierConfigResponse getTierConfig(@PathVariable("tierId") long tierId){
        return tierConfigService.getTierConfigById(tierId);
    }

    @DeleteMapping("/{tierId}")
    public String deleteTierConfig(@PathVariable long tierId) {
        tierConfigService.deleteTierConfigById(tierId);
        return "Deleted tier with id " + tierId;
    }

    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAllTierConfig() {
        tierConfigService.deleteAllTierConfigs();
        return ResponseEntity.ok("Deleted all tiers");
    }

}
