package com.nike.rewardmanagementsystem.services.impl;

import com.nike.rewardmanagementsystem.exceptions.CustomerNotFoundException;

import com.nike.rewardmanagementsystem.models.dtos.response.CashbackTransactionResponseDto;

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
