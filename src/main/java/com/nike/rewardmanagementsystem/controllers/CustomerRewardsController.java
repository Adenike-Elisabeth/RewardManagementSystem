package com.nike.rewardmanagementsystem.controllers;

import com.nike.rewardmanagementsystem.exceptions.CustomerNotFoundException;
import com.nike.rewardmanagementsystem.models.dtos.request.CustomerAddRewardRequest;
import com.nike.rewardmanagementsystem.models.dtos.response.ApiResponse;
import com.nike.rewardmanagementsystem.models.dtos.response.CashbackTransactionResponseDto;
import com.nike.rewardmanagementsystem.models.dtos.response.CustomerAddRewardResponse;
import com.nike.rewardmanagementsystem.models.dtos.response.CustomerRewardBalanceResponseDto;
import com.nike.rewardmanagementsystem.services.CustomerRewardService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rewards")
@RequiredArgsConstructor
@Slf4j
public class CustomerRewardsController {

    private final CustomerRewardService customerRewardService;

    @PostMapping("/add-reward")
    public ResponseEntity<ApiResponse<CustomerAddRewardResponse>> addReward(@RequestBody CustomerAddRewardRequest customerRequest) throws CustomerNotFoundException {
        log.info("Adding reward for customer: {}", customerRequest.getCustomerName());
        CustomerAddRewardResponse customerResponse = customerRewardService.addReward(customerRequest);
        return ResponseEntity.ok(new ApiResponse<>(customerResponse));
    }

//    @GetMapping("/balance")
//    public ResponseEntity<ApiResponse<CustomerRewardBalanceResponseDto>> getRewardsBalance(@RequestParam @NonNull Long customerId) throws CustomerNotFoundException {
//        log.info("Fetching reward balance for customer ID: {}", customerId);
//        CustomerRewardBalanceResponseDto balance = customerRewardService.getRewardsBalance(customerId);
//        return ResponseEntity.ok(new ApiResponse<>(balance));
//    }
@GetMapping("/balance/{customerId}")
public ResponseEntity<ApiResponse<CustomerRewardBalanceResponseDto>> getRewardsBalance(
        @PathVariable @NonNull Long customerId) throws CustomerNotFoundException {
    log.info("Fetching reward balance for customer ID: {}", customerId);
    CustomerRewardBalanceResponseDto balance = customerRewardService.getRewardsBalance(customerId);
    return ResponseEntity.ok(new ApiResponse<>(balance));
}



    @GetMapping("/history")
    public ResponseEntity<ApiResponse<List<CashbackTransactionResponseDto>>> getCashbackHistory(@RequestParam @NonNull Long customerId) {
        log.info("Fetching cashback history for customer ID: {}", customerId);
        List<CashbackTransactionResponseDto> history = customerRewardService.getCashbackHistory(customerId);
        return ResponseEntity.ok(new ApiResponse<>(history));
    }
}
