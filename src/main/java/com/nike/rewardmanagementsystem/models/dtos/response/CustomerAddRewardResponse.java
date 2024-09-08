package com.nike.rewardmanagementsystem.models.dtos.response;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerAddRewardResponse {
    private Long customerId;
    private String customerName;
    private BigDecimal totalCashback;
    private BigDecimal currentBalance;

}
