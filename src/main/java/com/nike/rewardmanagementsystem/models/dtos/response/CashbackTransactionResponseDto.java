package com.nike.rewardmanagementsystem.models.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CashbackTransactionResponseDto {
    private Long transactionId;
    private LocalDateTime transactionDate;
    private BigDecimal amountEarned;
    private String description;
}
