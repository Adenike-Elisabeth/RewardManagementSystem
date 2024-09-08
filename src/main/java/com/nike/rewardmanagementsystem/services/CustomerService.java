package com.nike.rewardmanagementsystem.services;

import com.nike.rewardmanagementsystem.models.dtos.request.CreateCustomerRequest;
import com.nike.rewardmanagementsystem.models.dtos.response.CreateCustomerResponse;

public interface CustomerService {
    CreateCustomerResponse createCustomer(CreateCustomerRequest customerRequest);
}
