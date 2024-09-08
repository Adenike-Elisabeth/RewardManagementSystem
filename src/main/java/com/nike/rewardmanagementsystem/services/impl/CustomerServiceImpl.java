package com.nike.rewardmanagementsystem.services.impl;


import com.nike.rewardmanagementsystem.exceptions.CustomerAlreadyExistsException;
import com.nike.rewardmanagementsystem.models.dtos.request.CreateCustomerRequest;
import com.nike.rewardmanagementsystem.models.dtos.response.CreateCustomerResponse;
import com.nike.rewardmanagementsystem.models.entities.Customer;
import com.nike.rewardmanagementsystem.repositories.CustomerRepository;
import com.nike.rewardmanagementsystem.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;


    @Override
    public CreateCustomerResponse createCustomer(CreateCustomerRequest customerRequest) {
        log.info("Creating a new customer with email: {}", customerRequest.getCustomerEmail());

        // Check if the customer already exists
        if (customerRepository.findByCustomerEmail(customerRequest.getCustomerEmail()).isPresent()) {
            log.error("Customer with email {} already exists", customerRequest.getCustomerEmail());
            throw new CustomerAlreadyExistsException("Customer with email " + customerRequest.getCustomerEmail() + " already exists.");
        }

        // Create a new customer entity
        Customer newCustomer = Customer.builder()
                .customerName(customerRequest.getCustomerName())
                .customerEmail(customerRequest.getCustomerEmail())
                .password(customerRequest.getPassword())  // In production, this should be encrypted
                .role(customerRequest.getRole())
                .build();

        // Save the new customer to the database
        Customer savedCustomer = customerRepository.save(newCustomer);

        // Build and return the response DTO
        return CreateCustomerResponse.builder()
                .customerId(savedCustomer.getCustomerId())
                .customerName(savedCustomer.getCustomerName())
                .customerEmail(savedCustomer.getCustomerEmail())
                .role(savedCustomer.getRole())
                .build();
    }
}
