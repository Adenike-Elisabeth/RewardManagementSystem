package com.nike.rewardmanagementsystem.services.impl;

import com.nike.rewardmanagementsystem.exceptions.CustomerNotFoundException;
import com.nike.rewardmanagementsystem.models.dtos.request.CustomerAddRewardRequest;
import com.nike.rewardmanagementsystem.models.dtos.response.CashbackTransactionResponseDto;
import com.nike.rewardmanagementsystem.models.dtos.response.CustomerAddRewardResponse;

import com.nike.rewardmanagementsystem.models.dtos.response.CustomerRewardBalanceResponseDto;
import com.nike.rewardmanagementsystem.models.entities.CashbackTransaction;
import com.nike.rewardmanagementsystem.models.entities.Customer;
import com.nike.rewardmanagementsystem.repositories.CashbackTransactionRepository;
import com.nike.rewardmanagementsystem.repositories.CustomerRepository;
import com.nike.rewardmanagementsystem.services.CustomerRewardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerRewardServiceImpl implements CustomerRewardService {

    private final CustomerRepository customerRepository;

    private final CashbackTransactionRepository cashbackTransactionRepository;


//    @Override
//    public CustomerAddRewardResponse addReward(CustomerAddRewardRequest customerRequest) throws CustomerNotFoundException {
//        log.info("Adding reward for customer: {}", customerRequest.getCustomerName());
//
//        Customer customer = customerRepository.findByCustomerName(customerRequest.getCustomerName());
//        if (customer == null) {
//            log.error("Customer with name {} not found", customerRequest.getCustomerName());
//            throw new CustomerNotFoundException("Customer with name " + customerRequest.getCustomerName() + " not found.");
//        }
//
//        customer.setTotalCashback(customer.getTotalCashback().add(customerRequest.getTotalCashback()));
//        customer.setCurrentBalance(customer.getCurrentBalance().add(customerRequest.getTotalCashback()));
//
//        Customer updatedCustomer = customerRepository.save(customer);
//
//        CustomerAddRewardResponse response = CustomerAddRewardResponse.builder()
//                .customerId(updatedCustomer.getCustomerId())
//                .customerName(updatedCustomer.getCustomerName())
//                .totalCashback(updatedCustomer.getTotalCashback())
//                .currentBalance(updatedCustomer.getCurrentBalance())
//                .build();
//
//        log.info("Reward added successfully for customer: {}", customerRequest.getCustomerName());
//        return response;
//    }
@Override
public CustomerAddRewardResponse addReward(CustomerAddRewardRequest customerRequest) throws CustomerNotFoundException {
    log.info("Adding reward for customer: {}", customerRequest.getCustomerName());

    Customer customer = customerRepository.findByCustomerName(customerRequest.getCustomerName());
    if (customer == null) {
        log.error("Customer with name {} not found", customerRequest.getCustomerName());
        throw new CustomerNotFoundException("Customer with name " + customerRequest.getCustomerName() + " not found.");
    }

    // Handle potential null values for totalCashback and currentBalance
    BigDecimal totalCashback = customer.getTotalCashback() != null ? customer.getTotalCashback() : BigDecimal.ZERO;
    BigDecimal currentBalance = customer.getCurrentBalance() != null ? customer.getCurrentBalance() : BigDecimal.ZERO;

    // Add the new cashback amount to the existing values
    customer.setTotalCashback(totalCashback.add(customerRequest.getTotalCashback()));
    customer.setCurrentBalance(currentBalance.add(customerRequest.getTotalCashback()));

    // Save the updated customer details
    Customer updatedCustomer = customerRepository.save(customer);

    // Build the response
    CustomerAddRewardResponse response = CustomerAddRewardResponse.builder()
            .customerId(updatedCustomer.getCustomerId())
            .customerName(updatedCustomer.getCustomerName())
            .totalCashback(updatedCustomer.getTotalCashback())
            .currentBalance(updatedCustomer.getCurrentBalance())
            .build();

    log.info("Reward added successfully for customer: {}", customerRequest.getCustomerName());
    return response;
}




    @Override
    public CustomerRewardBalanceResponseDto getRewardsBalance(Long customerId) throws CustomerNotFoundException {
        log.info("Fetching rewards balance for customer ID: {}", customerId);

        Customer customer = customerRepository.findByCustomerId(customerId);
        if (customer == null) {
            log.error("Customer with ID {} not found", customerId);
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
        }

        log.info("Customer with ID {} found. Total Cashback: {}, Current Balance: {}",
                customer.getCustomerId(), customer.getTotalCashback(), customer.getCurrentBalance());


        CustomerRewardBalanceResponseDto responseDto = new CustomerRewardBalanceResponseDto(
                customer.getCustomerId(),
                customer.getTotalCashback(),
                customer.getCurrentBalance()
        );

        log.info("Rewards balance for customer ID {} successfully retrieved.", customerId);
        return responseDto;
    }

    @Override
    public List<CashbackTransactionResponseDto> getCashbackHistory(Long customerId) {
        log.info("Fetching cashback history for customer ID: {}", customerId);

        List<CashbackTransaction> history = cashbackTransactionRepository.findByCustomerCustomerId(customerId);

        if (history.isEmpty()) {
            log.warn("No cashback transactions found for customer ID: {}", customerId);
        } else {
            log.info("{} cashback transactions found for customer ID: {}", history.size(), customerId);
        }

        List<CashbackTransactionResponseDto> historyDtoList = history.stream()
                .map(transaction -> new CashbackTransactionResponseDto(
                        transaction.getTransactionId(),
                        transaction.getTransactionDate(),
                        transaction.getAmountEarned(),
                        transaction.getDescription()
                ))
                .collect(Collectors.toList());

        log.info("Cashback history for customer ID {} successfully retrieved.", customerId);
        return historyDtoList;
    }
}
