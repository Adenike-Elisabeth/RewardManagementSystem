package com.nike.rewardmanagementsystem.models.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAddRewardRequest {
    private String customerName;
    private BigDecimal totalCashback;
    private BigDecimal currentBalance;

}
