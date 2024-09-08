package com.nike.rewardmanagementsystem.services;

import com.nike.rewardmanagementsystem.exceptions.CustomerNotFoundException;
import com.nike.rewardmanagementsystem.models.dtos.response.CashbackTransactionResponseDto;
import com.nike.rewardmanagementsystem.models.dtos.response.CustomerRewardBalanceResponseDto;

import java.util.List;

public interface CustomerRewardService {
    CustomerRewardBalanceResponseDto getRewardsBalance(Long customerId) throws CustomerNotFoundException;

    List<CashbackTransactionResponseDto> getCashbackHistory(Long customerId);
}
