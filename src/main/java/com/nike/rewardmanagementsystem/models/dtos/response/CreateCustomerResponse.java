package com.nike.rewardmanagementsystem.models.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCustomerResponse {

    private Long customerId;
    private String customerName;
    private String customerEmail;
    private String role;
}
