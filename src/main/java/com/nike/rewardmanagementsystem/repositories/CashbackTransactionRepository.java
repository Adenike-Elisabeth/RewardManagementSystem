package com.nike.rewardmanagementsystem.repositories;

import com.nike.rewardmanagementsystem.models.entities.CashbackTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CashbackTransactionRepository extends JpaRepository<CashbackTransaction, Long> {
    List<CashbackTransaction> findByCustomerCustomerId(Long customerId);
}
