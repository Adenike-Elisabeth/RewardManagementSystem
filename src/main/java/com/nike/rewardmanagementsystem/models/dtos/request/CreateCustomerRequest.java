package com.nike.rewardmanagementsystem.models.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCustomerRequest {
    private String customerName;
    private String customerEmail;
    private String password;
    private String role;

}
