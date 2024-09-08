package com.nike.rewardmanagementsystem.repositories;

import com.nike.rewardmanagementsystem.models.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByCustomerId(Long customerId);

}
