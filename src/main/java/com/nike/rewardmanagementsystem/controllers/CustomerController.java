package com.nike.rewardmanagementsystem.controllers;

import com.nike.rewardmanagementsystem.models.dtos.request.CreateCustomerRequest;
import com.nike.rewardmanagementsystem.models.dtos.response.ApiResponse;
import com.nike.rewardmanagementsystem.models.dtos.response.CreateCustomerResponse;
import com.nike.rewardmanagementsystem.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CreateCustomerResponse>> createCustomer(@RequestBody CreateCustomerRequest customerRequest) {
        log.info("Request to create a new customer with email: {}", customerRequest.getCustomerEmail());
        CreateCustomerResponse customerResponse = customerService.createCustomer(customerRequest);
        return ResponseEntity.ok(new ApiResponse<>(customerResponse));
    }
}
