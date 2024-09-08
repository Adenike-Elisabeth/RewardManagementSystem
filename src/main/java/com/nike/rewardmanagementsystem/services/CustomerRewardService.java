package com.nike.rewardmanagementsystem.services;

import com.nike.rewardmanagementsystem.exceptions.CustomerNotFoundException;
import com.nike.rewardmanagementsystem.models.dtos.request.CustomerAddRewardRequest;
import com.nike.rewardmanagementsystem.models.dtos.response.CashbackTransactionResponseDto;
import com.nike.rewardmanagementsystem.models.dtos.response.CustomerAddRewardResponse;
import com.nike.rewardmanagementsystem.models.dtos.response.CustomerRewardBalanceResponseDto;
import com.nike.rewardmanagementsystem.models.entities.CashbackTransaction;
import com.nike.rewardmanagementsystem.models.entities.Customer;

import java.util.List;

public interface CustomerRewardService {
    //CustomerResponse addReward(CustomerRequest customerRequest) throws CustomerNotFoundException;

    CustomerAddRewardResponse addReward(CustomerAddRewardRequest customerRequest) throws CustomerNotFoundException;

    CustomerRewardBalanceResponseDto getRewardsBalance(Long customerId) throws CustomerNotFoundException;

    List<CashbackTransactionResponseDto> getCashbackHistory(Long customerId);
}
