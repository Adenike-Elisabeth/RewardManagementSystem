package com.nike.rewardmanagementsystem.models.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRewardBalanceResponseDto {
        private Long customerId;
        private BigDecimal totalCashback;
        private BigDecimal currentBalance;
    }
